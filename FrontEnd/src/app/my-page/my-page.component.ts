import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from "../services/user.service";
import {Search} from "../models/search";
import {PenaltyService} from "../services/penalty.service";
import {Page, PagesForUser} from "../models/sheet";
import {OrderService} from "../services/order.service";
import {Location} from '@angular/common';
import { User} from "../models/user";

@Component({
  selector: 'app-user-page',
  templateUrl: './my-page.component.html',
  styleUrls: ['./my-page.component.css']
})
export class MyPageComponent implements OnInit {

  logIn: any;
  user!: any
  penalty: PagesForUser = new PagesForUser(null, 0, 1)
  orders: PagesForUser = new PagesForUser(null, 0, 1)
  searchParameter = new Search("id", "DESC", 1,2)
  searchPattern = {search:""}
  id = JSON.parse(localStorage.getItem("user")!).id;
  penaltyCreate = {idAccuser:this.id, idPenaltyKicker:0,idBook:0, description:""}

  constructor(private route: ActivatedRoute, private userService: UserService, private penaltyService: PenaltyService,
              private orderService: OrderService,private location: Location) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.location.back();
    }
    this.searchParameter.searchPattern = this.searchPattern
    this.route.paramMap.subscribe(params => {
      this.searchParameter.searchPattern.search = params.get('id')
      this.logIn = JSON.parse(localStorage.getItem("user")!)
      if (this.logIn.id != params.get('id')) {
          this.location.back();
      }
      console.log(1)
      this.userService.getOneUser(params.get('id')).subscribe((data: any) => {
        this.user = UserService.setUser(data)
        this.setPage("penalty")
        this.setPage("order")
      })
    })
  }

  setPage(table: string) {
    this.search(table)
  }

  search(service: string) {
    switch (service) {
      case "order":
        this.searchParameter.page = this.orders.page - 1;
        this.orderService.getOrder(this.searchParameter).subscribe((data: any) => {
          this.orders.content = data.content;
          this.orders.totalItem = data.totalItem;
        })
        break;
      case "penalty":
        this.searchParameter.page = this.penalty.page - 1;
        this.penaltyService.getPenalty(this.searchParameter).subscribe((data: Page) => {
          this.penalty.content = data.content;
          this.penalty.totalItem = data.totalItem;
        })
        break;
    }
  }

  abolition(order:number){
    this.orderService.abolition(this.id, order).subscribe(()=>{this.search("order")})
  }

  toreTheBook(){
    console.log(this.penaltyCreate)
    this.penaltyService.create(this.penaltyCreate).subscribe(()=>{this.setPage("penalty")})
  }

  setDescription(event:any){
    this.penaltyCreate.description = event.target.value
  }
  setIdUser(event:any){
    this.penaltyCreate.idPenaltyKicker = event.target.value
  }
  setIdBook(event:any){
    this.penaltyCreate.idBook = event.target.value
  }
}
