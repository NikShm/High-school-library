export class User {
  id: string;
  name: string;
  surname: string;
  login: string;
  password: string;
  role: string;
  type: string;
  createdAt: string;

  constructor(user: User) {
    if (user.id != "-1") {
      this.id = user.id;
      this.name = user.name;
      this.surname = user.surname;
      this.login = user.login
      this.password = user.password
      this.role = user.role;
      this.type = user.type;
      this.createdAt = user.createdAt;
    }else {
      this.id = "";
      this.name = "";
      this.surname = "";
      this.login = ""
      this.password = ""
      this.role = ""
      this.type = ""
      this.createdAt = ""
      }
  }
}
