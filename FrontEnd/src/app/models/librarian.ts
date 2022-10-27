import {User} from "./user";

export class Librarian extends User {
  constructor(librarian: Librarian) {
    super(librarian)
    this.position = librarian.position;
  }

  position: string;
}
