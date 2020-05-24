import React, { useEffect, useState } from 'react'
import fetch from 'node-fetch'

import Product from './Product'

//const url = 'http://gateway:2003/products/performance'
const url = 'http://localhost:2001/products/normal'

function ProductList() {
	const [products, setProducts] = useState([])
	const [loading, setLoading] = useState(true)

	useEffect(() => {
		fetch(url)
			.then((res) => res.json())
			.then((body) => {
				setProducts(body)
				setLoading(false)
			})
	}, [])

	return (
		<div className="product-list">
			<h1>This list contains all products: </h1>
			<p className="landing-text">
				Click <button onClick={()=>{
					let updated = [...products]
					updated[0].name = "Random " + Math.round(Math.random()*100)
					setProducts(updated)
				}}>here</button> to change the productlist that is shown.
			</p>
			<div className="products">
				{products.map((p) => (
					<Product key={p.productId} {...p}></Product>
				))}
			</div>
			{loading && <p className="landing-text">Loading ... </p>}
		</div>
	)
}

export default ProductList
