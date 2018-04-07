import { Course } from './../model/course.model';

import { selector } from 'rxjs/operator/publish';
import { Component } from '@angular/core';



@Component({
  selector: 'app-add-course',
  templateUrl: './addCourse.html'

})
export class AddCourseComponent {

  course: Course;

  addCourse(values: any) {
    this.course = new Course(values);
    console.log(this.course);
  }

}
