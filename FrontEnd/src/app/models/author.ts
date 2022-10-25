import {Book} from "./book";

export class Author{

  constructor(author:Author) {
    this.id = author.id
    this.name = author.name
    this.createdAt = author.createdAt
  }

  id:number;
  name:string;
  createdAt:string;
}
