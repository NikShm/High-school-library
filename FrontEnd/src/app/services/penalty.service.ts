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
export class PenaltyService {

  constructor(private http: HttpClient) {
  }

  getPenalty(searchParameter: Search): Observable<Page> {
    return this.http.post(GlobalConstants.apiURL +'/api/penalty/search', searchParameter).pipe(map((data: any) => {
      data.content =data.content.map((penalty:Penalty) => {
        return new Penalty(penalty)
      })
      return new Page(data.content, data.totalItem);
    }));
  }

  pay(id:number, idPenalty:string){
    return this.http.get(GlobalConstants.apiURL +'/api/penalty/'+id+ "/" +idPenalty);
  }

  create(penalty:any){
    return this.http.post(GlobalConstants.apiURL +'/api/penalty/create', penalty);
  }
}
