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
import {Librarian} from "../models/librarian";
import {Administrator} from "../models/administrator";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user = {id:null, role:"", isLogIn: false};

  constructor(private http: HttpClient, private router:Router) {
  }

  getUsers(searchParameter: Search): Observable<Page> {
    return this.http.post(GlobalConstants.apiURL +'/api/users/search', searchParameter).pipe(map((data: any) => {
      data.content = data.content.map((user:User) => {
        return UserService.setUser(user);
      })
      return new Page(data.content, data.totalItem);
    }));
  }

  getOneUser(id: any): Observable<any> {
    return this.http.get<User>(GlobalConstants.apiURL +'/api/users/id=' + id).pipe(map((data: any) => {
      return data;
    }));
  }

  static setUser(user:any):any{
    switch (user.type) {
      case "Student": {
        return new Student(user);
        break;
      }
      case "Teacher": {
        return new Teacher(user);
        break;
      }
      case "Librarian": {
        return new Librarian(user);
        break;
      }
      case "Administrator": {
        return new Administrator(user);
        break;
      }
    }
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
