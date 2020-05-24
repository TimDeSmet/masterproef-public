const express = require('express')
const puppeteer = require('puppeteer')
const httpProxy = require('http-proxy')
const mcache = require('memory-cache')

const app = express()
var apiProxy = httpProxy.createProxyServer()
const port = 3010
const webserver = `http://host.docker.internal:3002`
// const webserver = `http://localhost:3002`
const ua_regex =
	'/APIs-Google|Mediapartners-Google|AdsBot|Googlebot|FeedFetcher-Google|Google-Read-Aloud|DuplexWeb-Google|Google Favicon|Bingbot|AdIdxBot|BingPreview|Baiduspider|YandexBot|facebookexternalhit|Twitterbot/i'

function flatten(root) {
	// Haal alle onderliggende knopen op
	Array.from(root.querySelectorAll('*'))
		//Overloop de knopen die shadow inhoud bevatten
		.filter((element) => element.shadowRoot)
		.forEach((node) => {
			// Haal de elementen op uit de ShadowRoot
			node.shadowRoot.childNodes.forEach((shadowNode) => node.appendChild(shadowNode))

			// Recursief verder gaan
			flatten(node)
		})

	// Voor alle elementen die content zouden doorgeven aan een slot
	root.querySelectorAll('[slot]').forEach((n) => {
		let dest = n.getAttribute('slot')
		// Voeg de content aan het doelelement voor de slot
		n.parentNode.querySelector('slot[name=' + dest + ']').innerHTML = n.innerHTML
		// Verwijder slot
		n.remove()
	})
}

prerender = async (url) => {
	try {
		const browser = await puppeteer.launch({
			headless: true,
			args: ['--no-sandbox'],
		})
		const page = await browser.newPage()
		await page.goto(url, {waitUntil: 'networkidle0'})

		await page.$eval('html', flatten)
		const content = await page.content()

		await browser.close()
		return content
	} catch (err) {
		console.error(err)
	}
}

seoCheck = async (req, res, next) => {
	if (req.originalUrl === '/' && req.get('user-agent').match(ua_regex)) {
		let key = '__express__' + req.originalUrl
		let cached = mcache.get(key)
		if (!cached) {
			let content = await prerender(webserver + '/index.html')
			mcache.put(key, content, 60 * 10 * 1000)
			cached = mcache.get(key)
		}
		res.send(cached)
	} else {
		next()
	}
}

app.get('*', seoCheck, (req, res) => {
	apiProxy.web(req, res, { target: webserver })
})

app.listen(port, () => console.log(`Proxy server listening at http://localhost:${port}`))
