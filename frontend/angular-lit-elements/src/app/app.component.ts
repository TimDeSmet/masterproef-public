import { Component, HostListener } from '@angular/core';
import 'micro-frontend-poc-delaware-ugent'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})

export class AppComponent {

  message = null

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
}

