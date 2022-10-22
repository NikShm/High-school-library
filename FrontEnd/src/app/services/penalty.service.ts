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
export class PenaltyService {

  constructor(private http: HttpClient) {
  }

  getPenalty(searchParameter: SearchUser): Observable<Page> {
    return this.http.post('http://localhost:8080/api/penalty/search', searchParameter).pipe(map((data: any) => {
      data.content.forEach(function(penalty:Penalty,index:number){
        data.content[index] = new Penalty(penalty)
      })
      return new Page(data.content, data.pageCount, data.totalItem, data.page, data.pageSize);
    }));
  }
}
