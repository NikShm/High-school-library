import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../models/user";
import {Page} from "../models/page";
import {SearchUser} from "../models/search-user";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css','../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class UsersComponent implements OnInit {

  page:Page = new Page([],0,1,0,2);
  searchParameter = new SearchUser('',"id","ASC",null,2)


  constructor(private userService:UserService) { }

  ngOnInit(): void {
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
