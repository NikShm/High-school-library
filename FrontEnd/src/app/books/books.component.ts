import {Component, OnInit} from '@angular/core';
import {Page} from "../models/sheet";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {BooksService} from "../services/books.service";
import {Author} from "../models/author";
import {AuthorService} from "../services/author.service";
import {Search} from "../models/search";
import {OrderService} from "../services/order.service";
import {Order} from "../models/order";

@Component({
  selector: 'app-users',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css', '../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class BooksComponent implements OnInit {

  page: Page = new Page([], 0);
  searchParameter = new Search("id", "ASC", 0,2)
  searchPattern = {search:""}
  author!: Author | null;
  order = {idUser:JSON.parse(localStorage.getItem("user")!).id, book:{id:0}};


  constructor(private booksService: BooksService, private location: Location, private route: ActivatedRoute
              ,private orderService:OrderService){
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.location.back();
    }
    this.searchParameter.searchPattern = this.searchPattern
  }

  setSortField(field: string) {
    this.searchParameter.sortField = field;
    this.setPage()
  }

  setSearch(text: any) {
    this.searchParameter.searchPattern.search = text
    this.setPage()
  }

  setPageSize(pageSize: number) {
    this.searchParameter.pageSize = pageSize
    this.setPage()
  }

  setPage() {
    this.searchParameter.page -= 1
    this.search()
    this.searchParameter.page += 1
  }

  search() {
    this.booksService.getBooks(this.searchParameter).subscribe((data: any) => {
      this.page = data;
    })
  }

  setOrder(id:number){
    this.order.book.id = id
    this.orderService.setOrder(this.order)
  }
}
