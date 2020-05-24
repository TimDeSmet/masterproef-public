import { LitElement, html } from 'lit-element'

class Product extends LitElement {
	static get properties() {
		return {
			alt: { type: String },
		}
	}

	constructor() {
		super()
		this.alt = 'No alt text given for product image'
	}

	render() {
		console.log('render product')
		return html`
			<style>
				.mf-poc-product {
					text-align: center;
					padding: 10px;
				}
				.mf-poc-product-image {
					width: 45%;
					margin-top: 2rem;
				}
			</style>
			<div class="mf-poc-product">
				<img class="mf-poc-product-image" src='../assets/box.svg' alt='${this.alt}'></img>
                <h1><slot name="name">Default Name</slot></h1>
                <p><slot name="description">Default Description</slot></p>
                <p>€ <slot name="price">Default Price</slot></p>
            </div> 
        `
	}
}

customElements.define('product-element', Product)
