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

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user = {id:null, role:"", isLogIn: false};

  constructor(private http: HttpClient, private router:Router) {
  }

  getUsers(searchParameter: Search): Observable<Page> {
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

  authorize(login: string, password:string): Observable<LogIn>{
    return this.http.get<LogIn>(GlobalConstants.apiURL + '/api/users/login=' + login + "/password=" + password).pipe(map((data: LogIn) => {
      console.log(1)
      if(data != null) {
        this.user.id = data.id
        this.user.role = data.role
        this.user.isLogIn = true
        localStorage.setItem("user", JSON.stringify(this.user))
        this.router.navigate(["user/"+this.user.id])
      }else {
        window.alert("You entered the wrong login or password")
      }
      return new LogIn(null,"")
    }));
  }
}
