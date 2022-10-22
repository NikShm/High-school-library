import {Book} from "./book";

export class Author{

  constructor(author:Author) {
    this.id = author.id
    this.name = author.name
    this.surname = author.surname
    this.createdAt = author.createdAt
    this.books = author.books == null?null:author.books.forEach(function (book:Book,index:number=0){
      if (author.books) {
        author.books[index] = new Book(book)
      }
    })
  }

  id:number;
  name:string;
  surname:string;
  createdAt:string;
  books:Book[] | any;
}
