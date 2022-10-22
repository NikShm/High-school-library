import {User} from "./user";

export class Teacher extends User {
  constructor(teacher: Teacher) {
    super(teacher)
    this.cathedra = teacher.cathedra;
    this.degree = teacher.degree;
    this.rank = teacher.rank;

  }

  cathedra: string;
  degree: string;
  rank: string;
}
