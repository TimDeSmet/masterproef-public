import React from 'react'
//Inladen van npm package
import 'micro-frontend-poc-delaware-ugent'

function ProductList(props){
    return (
        <product-list expanding products={JSON.stringify(props.products)} ></product-list>
    )
}

export default ProductList

