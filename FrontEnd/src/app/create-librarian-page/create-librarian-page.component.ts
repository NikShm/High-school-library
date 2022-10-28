import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from "../services/user.service";
import {Search} from "../models/search";
import {PenaltyService} from "../services/penalty.service";
import {Page, PagesForUser} from "../models/sheet";
import {OrderService} from "../services/order.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-user-page',
  templateUrl: './create-librarian-page.component.html',
  styleUrls: ['./create-librarian-page.component.css']
})
export class CreateLibrarianPageComponent implements OnInit {

  logIn: any;
  user!: any
  librarian = {name:"", surname:"",login:"",password:"",role:"OPERATOR",type:"Librarian",position:""}
  id = JSON.parse(localStorage.getItem("user")!).id;

  constructor(private route: ActivatedRoute, private userService: UserService, private penaltyService: PenaltyService,
              private orderService: OrderService, private location: Location) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn && JSON.parse(localStorage.getItem("user")!) == 'ADMIN') {
      this.location.back();
    }
    this.route.paramMap.subscribe(params => {
      this.logIn = JSON.parse(localStorage.getItem("user")!)
      if (this.logIn.role == "USER") {
        this.location.back();
      }
    })
  }
  setName(event:any){
    this.librarian.name = event.target.value
  }
  setSurname(event:any){
    this.librarian.surname = event.target.value
  }
  setLogin(event:any){
    this.librarian.login = event.target.value
  }
  setPassword(event:any){
    this.librarian.password = event.target.value
  }
  setPosition(event:any){
    this.librarian.position = event.target.value
  }

  createLibrarian(){
    this.userService.createLibrarian(this.id, this.librarian)
  }
}
