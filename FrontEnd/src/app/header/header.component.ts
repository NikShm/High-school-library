import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class HeaderComponent implements OnInit {

  user = {id:null, role:"", isLogIn: false};
  id:any;

  constructor(private router:Router) {
  }

  logOut(){
    localStorage.setItem("user", JSON.stringify(this.user))
    this.router.navigate(['']);
  }

  ngOnInit(): void {
    this.id = JSON.parse(localStorage.getItem("user")!).id;
    this.user.role = JSON.parse(localStorage.getItem("user")!).role
  }
}
