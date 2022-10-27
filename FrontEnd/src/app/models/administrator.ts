import {User} from "./user";

export class Administrator extends User {
  constructor(administrator: Administrator) {
    super(administrator)
    this.degree = administrator.degree;
  }
  degree: string;
}
