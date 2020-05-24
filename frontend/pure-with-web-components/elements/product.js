class Product extends HTMLElement{
    
    connectedCallback(){
        const root = this.attachShadow({mode: 'closed'})
        let alt = this.getAttribute('image-alt') ? 
            this.getAttribute('image-alt') :  
            'No alt text given for product image'
        root.innerHTML = `
            <style>
                .product {
                    text-align: center;
                    padding: 10px;
                }
                .product-image{
                    width: 45%;
                    margin-top:2rem;
                }
            </style>
            
            <div class="product">
                <img class="product-image" src='../assets/box.svg' alt='${alt}'></img>
                <h1><slot name="name">Default Name</slot></h1>
                <p><slot name="description">Default Description</slot></p>
                <p>€ <slot name="price">Default Price</slot></p>
            </div> 
        `
    }
}

customElements.define('product-element', Product);