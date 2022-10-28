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
  templateUrl: './create-teacher-page.component.html',
  styleUrls: ['./create-teacher-page.component.css']
})
export class CreateTeacherPageComponent implements OnInit {

  logIn: any;
  user!: any
  teacher = {name:"", surname:"",login:"",password:"",role:"USER",type:"Teacher",cathedra:"",degree:"",rank:""}
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
    this.teacher.name = event.target.value
  }
  setSurname(event:any){
    this.teacher.surname = event.target.value
  }
  setLogin(event:any){
    this.teacher.login = event.target.value
  }
  setPassword(event:any){
    this.teacher.password = event.target.value
  }
  setCathedra(event:any){
    this.teacher.cathedra = event.target.value
  }
  setDegree(event:any){
    this.teacher.degree = event.target.value
  }
  setRank(event:any){
    this.teacher.rank = event.target.value
  }

  createTeacher(){
    this.userService.createTeacher(this.id, this.teacher)
  }
}
