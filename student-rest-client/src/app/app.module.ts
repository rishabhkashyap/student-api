import { StudentSerice } from './services/student.service';
import { AddStudentComponent } from './student/addStudent.component';
import { DeleteWarningComponent } from './course/deleteWarning.component';
import { DeleteCourseComponent } from './course/deleteCourse.component';
import { UpdateCourseComponent } from './course/UpdateCourse.component';
import { CourseService } from './services/course.service';
import { AddCourseComponent } from './course/addCourse.component';
import { FormsModule } from '@angular/forms';
import { CourseSearchService } from './services/courseSearch.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { routing } from '../app.route';
import { SearchComponent } from './search/search.component';
import { NavBarComponent } from './nav/nav.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';



import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    SearchComponent,
    AddCourseComponent,
    UpdateCourseComponent,
    DeleteCourseComponent,
    DeleteWarningComponent,
    AddStudentComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastModule.forRoot(),
    BsDatepickerModule.forRoot()

  ],
  providers: [
    CourseSearchService,
    CourseService,
    StudentSerice
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
