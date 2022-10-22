import {User} from "./user";

export class Student extends User {
  constructor(student: Student) {
    super(student)
    this.faculty = student.faculty;
    this.group = student.group;
    this.subgroup = student.subgroup;
  }

  faculty: string;
  group: string;
  subgroup: string;
}
