import {Author} from "./author";

export class Book {

  constructor(book: any) {
    this.id = book.id
    this.name = book.name
    this.description = book.description
    this.price = book.price
    this.category = book.category
    this.count = book.count
    this.createdAt = book.createdAt
    this.authors = book.authorList == null?null:book.authorList.map((author: Author) => {
     return new Author(author)
    })
  }

  id: number;
  name: string;
  description: string;
  price: number;
  category: string;
  count: number;
  createdAt: string;
  authors: Author[] | any;
}
