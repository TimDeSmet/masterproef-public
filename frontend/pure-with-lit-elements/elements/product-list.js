import { LitElement, html } from 'lit-element'

class ProductList extends LitElement {
	static get properties() {
		return {
			expanding: { type: Boolean },
			message: { type: String, attribute: false },
			toggleText: { type: String, attribute: false },
			displayedProducts: { type: Array, attribute: false },
			short: { type: Boolean, attribute: false },
			products: { type: String },
		}
	}

	constructor() {
		super()
		this.expanding = false
		this.products = "[]"
	}

	connectedCallback() {
		super.connectedCallback()
		if (this.expanding) {
			this.handleToggle(this.expanding)
		}

		document.addEventListener('container:trigger', () => {
			this.message = 'You pressed the button in the container application.'
			setTimeout(() => {
				this.message = ''
			}, 3000)
		})
	}

	handleToggle(val) {
		this.short = val
		this.toggleText = (val ? 'Expand' : 'Collapse') + ' list'
	}

	dispatchEvent() {
		document.dispatchEvent(
			new CustomEvent('products:trigger', {
				detail: {
					amount: JSON.parse(this.products).length,
				},
			})
		)
	}

	render() {
		console.log('render list')
		return html` 
			<style>
				p,
				button {
					font-size: 1.25rem;
					font-weight: 300;
				}

				button {
					text-decoration: underline;
					color: var(--primary);
					border: none;
				}

				button:focus {
					outline: none;
				}

				button:hover {
					cursor: pointer;
				}

				.product-list {
					margin: 0 10%;
					background-color: white;
					box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
					overflow: auto;
					margin-top: 1rem;
					padding: 3rem;
				}

				.product-list > h1 {
					margin: 3rem 0rem;
				}

				#products {
					display: flex;
					flex-wrap: wrap;
					justify-content: space-evenly;
				}

				product-element {
					width: 30%;
					margin: 1rem 0;
					box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
				}

				.header {
					display: flex;
					justify-content: space-between;
				}

				.credit {
					font-size: 0.7rem;
				}
			</style>

			<div class="product-list">
				<div class="header">
					<h1>This list contains all products:</h1>
					${this.expanding
						? html`<button @click="${() => this.handleToggle(!this.short)}">
								${this.toggleText}
						  </button>`
						: null}
				</div>
				<p id="responder">${this.message}</p>
				<p></p>
				<p>
					Click <button @click="${this.dispatchEvent}">here</button> to communicate with
					the micro frontend.
				</p>
				${JSON.parse(this.products).length
					? html`
							<div id="products">
								${JSON.parse(this.products)
									.slice(
										0,
										this.short
											? JSON.parse(this.products).length < 3
												? JSON.parse(this.products).length
												: 3
											: JSON.parse(this.products).length
									)
									.map(
										(p) => html`
											<product-element alt="${'Image of a ' + p.name}">
												<span slot="name">${p.name}</span>
												<span slot="description">${p.description}</span>
												<span slot="price">${p.price.toFixed(2)}</span>
											</product-element>
										`
									)}
							</div>
					  `
					: null}
				${!JSON.parse(this.products.length) ? html`<p>No products found...</p>` : null}
				${JSON.parse(this.products.length)
					? html`<p class="credit">
							Icons made by
							<a href="https://www.flaticon.com/authors/icongeek26" title="Icongeek26"
								>Icongeek26</a
							>
							from
							<a href="https://www.flaticon.com/" title="Flaticon"
								>www.flaticon.com</a
							>
					  </p>`
					: null}
			</div>`
	}

	disconnectedCallback() {
		document.removeEventListener('container:trigger')
	}
}

customElements.define('product-list', ProductList)
