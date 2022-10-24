import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = "";

  password = "";

  constructor(private userService: UserService) { }

  submit(){
     this.userService.authorize(this.login, this.password).subscribe()
  }

  ngOnInit(): void {
  }

}
