import React from 'react'
import './App.css'

import ProductList from './components/ProductList'

const background = require('./assets/bg.png')

function App() {
	return (
		<div className="app-container">
			<div>
				<hr />
				<div className="page-titles">
					<h1 className="big-title">Micro Frontend<br />POC <span className="red version">React</span></h1>
					<h1 className="medium-title">delaware <span className="red">-</span> UGent</h1>
				</div>
			</div>
			<p className="landing-text">
				This app was created with <a href="https://reactjs.org/">React</a>. It acts as the frontend monolith that will be the baseline for the POC.
			</p>
			<ProductList></ProductList>
			<img className="bg-image" src={background} alt="background hexagonal shapes"></img>
		</div>
	)
}

export default App
