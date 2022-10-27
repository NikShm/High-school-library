import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {UserPageComponent} from "./user-page/user-page.component";
import {LoginComponent} from "./login/login.component";
import {FormsModule} from "@angular/forms";
import {BooksComponent} from "./books/books.component";
import {AuthorComponent} from "./author/author.component";
import {MyPageComponent} from "./my-page/my-page.component";
import {CreateStudentPageComponent} from "./create-student-page/create-student-page.component";
import {CreateLibrarianPageComponent} from "./create-librarian-page/create-librarian-page.component";
import {CreateTeacherPageComponent} from "./create-teacher-page/create-teacher-page.component";


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'users', component: UsersComponent},
  {path: 'books', component: BooksComponent},
  {path: 'user/:id', component: UserPageComponent},
  {path: 'my-page/:id', component: MyPageComponent},
  {path: 'author/:id', component: AuthorComponent},
  {path: 'create-student', component: CreateStudentPageComponent},
  {path: 'create-teacher', component: CreateTeacherPageComponent},
  {path: 'create-librarian', component: CreateLibrarianPageComponent},
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    anchorScrolling: 'enabled',
    scrollPositionRestoration: 'enabled'
  }),
  FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
