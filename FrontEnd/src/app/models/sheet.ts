import {User} from "./user";
import {Penalty} from "./penalty";
import {Order} from "./order";
import {Student} from "./student";
import {Teacher} from "./teacher";

export class Page {
  constructor(content: any, pageCount: number, totalItem: number, page: number, pageSize: number) {
    this.content = content;
    this.pageCount = pageCount;
    this.totalItem = totalItem;
    this.page = page;
    this.pageSize = pageSize;
  }

  content:any;
  pageCount : number;
  totalItem : number;
  page : number;
  pageSize : number;
}
