import {Component, OnInit} from '@angular/core';
import {Page} from "../models/sheet";
import {SearchAuthorsBook} from "../models/search";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {BooksService} from "../services/books.service";
import {Author} from "../models/author";
import {AuthorService} from "../services/author.service";

@Component({
  selector: 'app-users',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css', '../../assets/css/bootstrap.css', '../../assets/css/font-awesome.css', '../../assets/css/style.css']
})
export class BooksComponent implements OnInit {

  page: Page = new Page([], 0);
  searchParameter = new SearchAuthorsBook('', "id", "ASC", 0, 2, null)
  author!: Author | null;


  constructor(private booksService: BooksService, private location: Location, private route: ActivatedRoute,
              private authorService: AuthorService) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn) {
      this.location.back();
    }
    this.route.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        this.authorService.getOneAuthor(params.get('id')).subscribe((data: Author) => {
          this.author = data
          this.searchParameter.authorId = this.author.id
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
    this.searchParameter.search = text
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
