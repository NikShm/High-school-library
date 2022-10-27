import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import {AppRoutingModule} from "./app-routing.module";
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {FormsModule,ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import { UserPageComponent } from './user-page/user-page.component';
import {UserService} from "./services/user.service";
import {LoginComponent} from "./login/login.component";
import {BooksComponent} from "./books/books.component";
import {AuthorComponent} from "./author/author.component";
import {MyPageComponent} from "./my-page/my-page.component";
import {CreateStudentPageComponent} from "./create-student-page/create-student-page.component";
import {CreateLibrarianPageComponent} from "./create-librarian-page/create-librarian-page.component";
import {CreateTeacherPageComponent} from "./create-teacher-page/create-teacher-page.component";

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    HeaderComponent,
    FooterComponent,
    UserPageComponent,
    LoginComponent,
    BooksComponent,
    AuthorComponent,
    MyPageComponent,
    CreateStudentPageComponent,
    CreateLibrarianPageComponent,
    CreateTeacherPageComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
