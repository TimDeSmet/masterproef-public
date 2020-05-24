import React, { useEffect, useState } from 'react'
import './App.css'

import 'micro-frontend-pre-poc-delaware-ugent'

const background = require('./assets/bg.png')
const url = 'http://gateway:2003/products/normal'

function App() {
	const [message, setMessage] = useState(null)
	const [products, setProducts] = useState([])

	useEffect(() => {
		
		fetch(url)
			.then((res) => res.json())
			.then((body) => {
				setProducts(body)
			})

		document.addEventListener('products:trigger', (event) => {
			setMessage(
				`Received a message from the micro frontend: ${event.detail.amount} products are displayed`
			)
			setTimeout(() => {
				setMessage(null)
			}, 10000)
		})
		return () => {
			document.removeEventListener('products:trigger')
		}
	}, [])

	return (
		<div className="app-container">
			<div>
				<hr />
				<div className="page-titles">
					<h1 className="big-title">
						Micro Frontend
						<br />
						POC <span className="red version">React + Web Components</span>
					</h1>
					<h1 className="medium-title">
						delaware <span className="red">-</span> UGent
					</h1>
				</div>
			</div>
			<p className="landing-text">
				This app was created with <a href="https://reactjs.org/">React</a>. It acts as the
				frontend monolith that will be the baseline for the POC.
			</p>
			<p>
				Click{' '}
				<button
					onClick={() => {
						document.dispatchEvent(new CustomEvent('container:trigger'))
					}}
				> 
					here
				</button>{' '}
				to communicate with the micro frontend.
			</p>
			<p className="landing-text">
				Click <button onClick={()=>{
					let updated = [...products]
					updated[0].name = "Random " + Math.round(Math.random()*100)
					setProducts(updated)
				}}>here</button> to change the productlist that is shown.
			</p>
			{message && <p className="landing-text">{message}</p>}
			<product-list expanding products={JSON.stringify(products)}></product-list> 
			<img className="bg-image" src={background} alt="background hexagonal shapes"></img>
		</div> 
	)
}

export default App
