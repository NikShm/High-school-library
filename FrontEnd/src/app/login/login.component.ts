import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = "";

  password = "";

  constructor(private userService: UserService, private rout:Router) { }

  submit(){
     this.userService.authorize(this.login, this.password).subscribe()
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.rout.navigate(["/my-page"+JSON.parse(localStorage.getItem("user")!).id]);
    }
  }

}
