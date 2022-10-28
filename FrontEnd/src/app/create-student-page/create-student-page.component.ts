import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from "../services/user.service";
import {Search} from "../models/search";
import {PenaltyService} from "../services/penalty.service";
import {Page, PagesForUser} from "../models/sheet";
import {OrderService} from "../services/order.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-user-page',
  templateUrl: './create-student-page.component.html',
  styleUrls: ['./create-student-page.component.css']
})
export class CreateStudentPageComponent implements OnInit {

  logIn: any;
  user!: any
  student = {name:"", surname:"",login:"",password:"",role:"USER",type:"Student",faculty:"",group:"",subgroup:""}
  id = JSON.parse(localStorage.getItem("user")!).id;

  constructor(private route: ActivatedRoute, private userService: UserService, private penaltyService: PenaltyService,
              private orderService: OrderService, private location: Location) {
  }

  ngOnInit(): void {
    if (!JSON.parse(localStorage.getItem("user")!).isLogIn && JSON.parse(localStorage.getItem("user")!) == 'ADMIN') {
      this.location.back();
    }
    this.route.paramMap.subscribe(params => {
      this.logIn = JSON.parse(localStorage.getItem("user")!)
      if (this.logIn.role == "USER") {
        this.location.back();
      }
    })
  }
  setName(event:any){
    this.student.name = event.target.value
  }
  setSurname(event:any){
    this.student.surname = event.target.value
  }
  setLogin(event:any){
    this.student.login = event.target.value
  }
  setPassword(event:any){
    this.student.password = event.target.value
  }
  setFaculty(event:any){
    this.student.faculty = event.target.value
  }
  setGroup(event:any){
    this.student.group = event.target.value
  }
  setSubgroup(event:any){
    this.student.subgroup = event.target.value
  }
  createStudent(){
    this.userService.createStudent(this.id, this.student)
  }
}
