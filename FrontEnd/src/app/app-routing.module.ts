import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {UserPageComponent} from "./user-page/user-page.component";
import {LoginComponent} from "./login/login.component";
import {FormsModule} from "@angular/forms";
import {BooksComponent} from "./books/books.component";


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'users', component: UsersComponent},
  {path: 'books', component: BooksComponent},
  {path: 'user/:id', component: UserPageComponent},
  {path: 'author/:id', component: BooksComponent},
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
