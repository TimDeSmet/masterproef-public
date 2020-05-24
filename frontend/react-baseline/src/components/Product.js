import React from 'react'

let im = require('../assets/box.svg')

function Product(props) {
	return (
		<div className="product">
            <img className="product-image" src={im} alt={"image of product: " + props.name} ></img>
			<h2>{props.name}</h2>
			<p>{props.description}</p>
			<p>€ {props.price.toFixed(2)}</p>
		</div>
	)
}

export default Product
