import {User} from "./user";
import {Penalty} from "./penalty";
import {Order} from "./order";
import {Student} from "./student";
import {Teacher} from "./teacher";

export class Page {
  constructor(content: any, totalItem: number) {
    this.content = content;
    this.totalItem = totalItem;
  }
  content:any;
  totalItem : number;
}

export class PagesForUser extends Page{
  constructor(content: any, totalItem: number, page:number) {
    super(content,totalItem)
    this.page = page
  }
  page: number;
}
