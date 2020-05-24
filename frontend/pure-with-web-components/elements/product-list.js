class ProductList extends HTMLElement {

	constructor(){
		super()
		this.root = this.attachShadow({mode: 'closed'})
	}

	static get observedAttributes() {
        return ['expanding', 'short', 'products'];
    }

    attributeChangedCallback(attrName){
		if(attrName === 'short'){
			this.root.getElementById('products') && this.populateList()
		}
		if(attrName === 'products'){
			this.getAttribute('products') !== '[]' && this.populateList()
		}
	}
	
	populateList(){
		let product_list = JSON.parse(this.getAttribute('products'))
		if(this.hasAttribute('expanding')){
			product_list = this.hasAttribute('short') ? JSON.parse(this.getAttribute('products')).slice(0,3) : JSON.parse(this.getAttribute('products'))
			this.root.getElementById('toggle').innerHTML = (this.hasAttribute('short') ? 'Expand' : 'Collapse') + ' list'
		}
		this.root.getElementById('products').innerHTML = product_list.map(
			p => `
			<product-element image-alt='${"Image of a " + p.name}'>
				<span slot="name">${p.name}</span>
				<span slot="description">${p.description}</span>
				<span slot="price">${p.price.toFixed(2)}</span>
			</product-element>
		`).join(' ')
	}

	makeExpandable(){
		this.setAttribute('short', '')
		this.root.getElementById('toggle').addEventListener('click', () => {
			if(this.hasAttribute('short')){
				this.removeAttribute('short')
			}else{
				this.setAttribute('short', '')
			}
		})
	}

    connectedCallback() {
		this.render()
		this.hasAttribute('expanding') && this.makeExpandable()
		this.populateList()

		document.addEventListener('container:trigger', () => {
			this.root.getElementById('responder').innerText= "You pressed the button in the container application."
			setTimeout(() => {
				this.root.getElementById('responder').innerText = ""
			}, 3000)
		})
		
		this.root.getElementById('trigger').addEventListener('click', () => {
			document.dispatchEvent(new CustomEvent('products:trigger', {
				detail: {
					amount: JSON.parse(this.getAttribute('products')).length
				}
			}))
		})
	}

	render(){
		this.root.innerHTML = `
			<style>
				p, button {
					font-size: 1.25rem;
					font-weight: 300;
				}

				button {
					text-decoration: underline;
					color: var(--primary);
					border: none;
				}

				button:focus {
					outline:none;
				}

				button:hover {
					cursor: pointer;
				}
				
				.product-list {
					margin: 0 10%;
					background-color: white;
					box-shadow: 0 .125rem .25rem rgba(0,0,0,.075);
					overflow:auto;
					margin-top:1rem;
					padding: 3rem;
				}
				
				.product-list>h1 {
					margin: 3rem 0rem;
				}
				
				#products {
					display: flex;
					flex-wrap: wrap;
					justify-content: space-evenly;
				}

				product-element{
					width: 30%;
					margin: 1rem 0;
                    box-shadow: 0 .125rem .25rem rgba(0,0,0,.075);
				}
				
				.header{
					display: flex;
					justify-content: space-between;
				}

			</style>
			<div class="product-list">
				<div class="header">
					<h1>This list contains all products: </h1>
					<button id="toggle"></button>
				</div>
				<p id="responder"><p>
				<div id="products"></div>
			</div>`
	}

	disconnectedCallback(){
		this.root.getElementById('toggle').removeEventListener('click')
		this.root.getElementById('trigger').removeEventListener('click')
		document.removeEventListener('container:trigger')
	}
}

customElements.define('product-list', ProductList)
