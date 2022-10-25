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
import {Author} from "../models/author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  user = {id:null, role:"", isLogIn: false};

  constructor(private http: HttpClient, private router:Router) {
  }

  getAuthors(searchParameter: Search): Observable<Page> {
    return this.http.post(GlobalConstants.apiURL +'/api/authors/search', searchParameter).pipe(map((data: any) => {
      data.content.map((author:Author)=>{
        return new Author(author);
      })
      return new Page(data.content, data.totalItem);
    }));
  }

  getOneAuthor(id: any): Observable<Author> {
    return this.http.get<Author>(GlobalConstants.apiURL +'/api/authors/id=' + id).pipe(map((data: any) => {
      return new Author(data);
    }));
  }
}
