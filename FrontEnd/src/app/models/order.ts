import {Book} from "./book";

export class Order{

  constructor(order:Order) {
    this.id = order.id
    this.idUser = order.idUser
    this.book = new Book(order.book)
    this.orderDate = order.orderDate
    this.dateOfIssue = order.dateOfIssue
    this.returnDate = order.returnDate
    this.status = order.status
  }

  id:number;
  idUser:number;
  book:Book;
  orderDate:string;
  dateOfIssue:string;
  returnDate:string;
  status:string;
}
