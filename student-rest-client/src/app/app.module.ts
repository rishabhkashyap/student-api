import { AddCourseComponent } from './course/addCourse.component';
import { FormsModule } from '@angular/forms';
import { CourseSearchService } from './services/courseSearch.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { routing } from '../app.route';
import { SearchComponent } from './search/search.component';
import { NavBarComponent } from './nav/nav.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';


import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    SearchComponent,
    AddCourseComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [CourseSearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
