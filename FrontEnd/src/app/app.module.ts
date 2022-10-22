import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import {AppRoutingModule} from "./app-routing.module";
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {FormsModule} from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import { UserPageComponent } from './user-page/user-page.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    HeaderComponent,
    FooterComponent,
    UserPageComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
