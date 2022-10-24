import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user = {id:null, role:"", isLogIn: false};
  ngOnInit(){
    if(localStorage.getItem("user") == null){
      localStorage.setItem("user",JSON.stringify(this.user))
    }
  }
}
