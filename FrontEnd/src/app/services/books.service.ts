import {Injectable} from "@angular/core";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Student} from "../models/student";
import {Search} from "../models/search";
import {Page} from "../models/sheet";
import {Teacher} from "../models/teacher";
import {GlobalConstants} from "../global-constants";
import {LogIn} from "../models/log-in";
import {Router} from "@angular/router";
import {Order} from "../models/order";
import {Book} from "../models/book";

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  user = {id:null, role:"", isLogIn: false};

  constructor(private http: HttpClient, private router:Router) {
  }

  getBooks(searchParameter: any): Observable<Page> {
    return this.http.post(GlobalConstants.apiURL +'/api/books/search', searchParameter).pipe(map((data: any) => {
      data.content.forEach(function(book:Book,index:number){
        data.content[index] = new Book(book)
      })
      return new Page(data.content, data.totalItem);
    }));
  }
}
