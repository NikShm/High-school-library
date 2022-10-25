import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {Page} from "../models/sheet";
import {Search} from "../models/search";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css','../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class UsersComponent implements OnInit {

  page:Page = new Page([],0);
  searchParameter = new Search('',"id","ASC",0,2)


  constructor(private userService:UserService,private location: Location) { }

  ngOnInit(): void {
    if(!JSON.parse(localStorage.getItem("user")!).isLogIn || JSON.parse(localStorage.getItem("user")!).role == "USER"){
      this.location.back();
    }
    this.search()
  }

  setSortField(field:string){
    this.searchParameter.sortField = field;
    this.setPage()
  }

  setSearch(text:any){
    this.searchParameter.search = text
    this.setPage()
  }

  setPageSize(pageSize:number){
    this.searchParameter.pageSize = pageSize
    this.setPage()
  }

  setPage(){
    this.searchParameter.page -= 1;
    this.search()
    this.searchParameter.page += 1;
  }

  search() {
    this.userService.getUsers(this.searchParameter).subscribe((data:any)=>{this.page = data})
  }
}
