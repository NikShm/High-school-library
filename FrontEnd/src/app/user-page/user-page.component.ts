import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {Student} from "../models/student";
import {SearchUser} from "../models/search-user";
import {PenaltyService} from "../services/penalty.service";
import {Page} from "../models/page";
import {OrderService} from "../services/order.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user!: any
  penalty: Page = new Page(null,0,0,1,2)
  orders:Page =  new Page(null,0,0,1,2)
  searchParameter = new SearchUser("","id","ASC",1,2)

  constructor(private route: ActivatedRoute, private userService: UserService, private penaltyService: PenaltyService, private orderService:OrderService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.searchParameter.search = params.get('id')
      this.userService.getOneUser(params.get('id')).subscribe((data: any) => {
        this.user = data
        this.setPage("penalty")
        this.setPage("order")
      })
    })
  }
  setPage(table:string){
    this.search(table)
  }

  search(service:string) {
    switch (service){
      case "order":
        this.searchParameter.page = this.orders.page - 1;
        this.orderService.getOrder(this.searchParameter).subscribe((data:any)=>{this.orders = data;this.orders.page += 1;})
        break;
      case "penalty":
        this.searchParameter.page = this.penalty.page - 1;
        this.penaltyService.getPenalty(this.searchParameter).subscribe((data:Page)=>{this.penalty = data; this.penalty.page += 1})
        break;
    }
  }
show(){
  console.log(this.user)
}
}
