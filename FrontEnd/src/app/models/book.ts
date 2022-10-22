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
    this.authors = book.authorList
    this.authors.forEach(function (author: Author, index: number) {
      book.authorList[index] = new Author(author)
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
