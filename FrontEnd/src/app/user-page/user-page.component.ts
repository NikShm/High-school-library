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
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  logIn: any;
  user!: any
  penalty: PagesForUser = new PagesForUser(null, 0, 1)
  orders: PagesForUser = new PagesForUser(null, 0, 1)
  searchParameter = new Search("id", "DESC", 1, 2)
  searchPattern = {search: ""}
  id = JSON.parse(localStorage.getItem("user")!).id;

  constructor(private route: ActivatedRoute, private userService: UserService, private penaltyService: PenaltyService,
              private orderService: OrderService, private location: Location) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.location.back();
    }
    this.searchParameter.searchPattern = this.searchPattern
    this.route.paramMap.subscribe(params => {
      this.searchParameter.searchPattern.search = params.get('id')
      this.logIn = JSON.parse(localStorage.getItem("user")!)
      if (this.logIn.role == "USER") {
        this.location.back();
      }
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

  toIssue(order: any) {
    this.orderService.toIssue(this.id, order).subscribe(() => {
      this.setPage("order")
    })
  }

  pay(idPenalty: string) {
    this.penaltyService.pay(this.id, idPenalty).subscribe(() => {
      this.setPage("penalty")
    })
  }

  returningLate(order: any) {
    this.orderService.returningLate(this.id, order).subscribe(() => {
      this.setPage("order")
    })
  }

  returning(order: any) {
    this.orderService.returning(this.id, order).subscribe(() => {
      this.setPage("order")
    })
  }
}
