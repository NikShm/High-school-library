import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Search} from "../models/search";
import {Observable} from "rxjs";
import {Page} from "../models/sheet";
import {map} from "rxjs/operators";
import {Order} from "../models/order";
import {Penalty} from "../models/penalty";
import {GlobalConstants} from "../global-constants";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  getOrder(searchParameter: Search): Observable<Page> {
    return this.http.post(GlobalConstants.apiURL +'/api/order/search', searchParameter).pipe(map((data: any) => {
      data.content = data.content.map((order:any) => {
        return new Order(order);
      })
      return new Page(data.content, data.totalItem);
    }));
  }
}
