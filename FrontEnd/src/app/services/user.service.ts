import {Injectable} from "@angular/core";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Student} from "../models/student";
import {SearchUser} from "../models/search-user";
import {Page} from "../models/page";
import {Teacher} from "../models/teacher";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUsers(searchParameter: SearchUser): Observable<Page> {
    return this.http.post('http://localhost:8080/api/users/search', searchParameter).pipe(map((data: any) => {
      return new Page(data.content, data.pageCount, data.totalItem, data.page, data.pageSize);
    }));
  }

  getOneUser(id: any): Observable<User> {
    return this.http.get<User>('http://localhost:8080/api/users/id=' + id).pipe(map((data: any) => {
      switch (data.type) {
        case "Student": {
          return new Student(data);
          break;
        }
        case "Teacher": {
          return new Teacher(data);
          break;
        }
      }
      return new User(data);
    }));
  }
}
