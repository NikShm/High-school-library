import {Component, OnInit} from '@angular/core';
import {Page} from "../models/sheet";
import {Search} from "../models/search";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {BooksService} from "../services/books.service";
import {Author} from "../models/author";
import {AuthorService} from "../services/author.service";

@Component({
  selector: 'app-users',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css', '../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class AuthorComponent implements OnInit {

  page: Page = new Page([], 0);
  searchParameter = new Search("id", "ASC", 0, 2)
  searchPattern = {search:"",authorId:0}
  author!: Author | null;


  constructor(private booksService: BooksService, private location: Location, private route: ActivatedRoute,
              private authorService: AuthorService) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.location.back();
    }
    this.searchParameter.searchPattern = this.searchPattern
    this.route.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        this.authorService.getOneAuthor(params.get('id')).subscribe((data: Author) => {
          this.author = data
          this.searchPattern.authorId = this.author.id
          this.setPage()
        })
      }
    })
  }

  setSortField(field: string) {
    this.searchParameter.sortField = field;
    this.setPage()
  }

  setSearch(text: any) {
    this.searchPattern.search = text
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
}
