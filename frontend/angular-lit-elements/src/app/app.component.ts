import { Component, HostListener, OnInit } from '@angular/core';
import fetch from 'node-fetch'
import 'micro-frontend-poc-delaware-ugent'

const url = 'http://gateway:2003/products/normal'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})

export class AppComponent implements OnInit {

  message = null

  products = []

  pAttr = ""

  ngOnInit(){
    fetch(url)
			.then((res) => res.json())
			.then((body) => {
        this.products = body
        this.pAttr = JSON.stringify(this.products)
			})
  }
  
  @HostListener('document:products:trigger', ['$event'])
  setMessage(event){
    this.message = `Received a message from the micro frontend: ${event.detail.amount} products are displayed`
    setTimeout(()=>{
      this.message = null
    }, 3000)
  }
  
  sendEvent(){
    document.dispatchEvent(new CustomEvent('container:trigger'));
  }

  handleClick(){
    this.products[0].name = "Random " + Math.round(Math.random()*100)
    this.pAttr = JSON.stringify(this.products)
  }
}

