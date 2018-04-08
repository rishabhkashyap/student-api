import { injectViewContainerRef } from '@angular/core/src/render3';
import { ApiError } from '../model/ApiError.model';
import { StudentResponse } from '../model/StudentResponse.model';
import { CourseService } from './../services/course.service';
import { Course } from './../model/course.model';

import { selector } from 'rxjs/operator/publish';
import { Component } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ViewContainerRef } from '@angular/core';



@Component({
  selector: 'app-add-course',
  templateUrl: './addCourse.html'

})
export class AddCourseComponent {

  course: Course;
  apiError: ApiError;

  constructor(private courseService: CourseService, private toastr: ToastsManager, private vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);

  }

  onSubmit(values: any) {

    this.course = new Course(values);
    this.courseService.addCourse(this.course)
      .subscribe((data) => {
        if (data != null) {
          console.log('server response = ' + data);
          this.toastr.success(data.getMessage());
        }
      },
      (error) => {
        if (error != null) {
          this.apiError = new ApiError(error);
          this.toastr.error(this.apiError.getMessage());
      } else {
        this.toastr.error('Something went wrong.Try again later..');

      }
    });


  }

}
