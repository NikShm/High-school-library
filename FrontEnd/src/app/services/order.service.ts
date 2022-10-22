import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {SearchUser} from "../models/search-user";
import {Observable} from "rxjs";
import {Page} from "../models/page";
import {map} from "rxjs/operators";
import {Order} from "../models/order";
import {Penalty} from "../models/penalty";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  getOrder(searchParameter: SearchUser): Observable<Page> {
    return this.http.post('http://localhost:8080/api/order/search', searchParameter).pipe(map((data: any) => {
      data.content.forEach(function(order:Order,index:number){
        data.content[index] = new Order(order)
      })
      return new Page(data.content, data.pageCount, data.totalItem, data.page, data.pageSize);
    }));
  }
}
