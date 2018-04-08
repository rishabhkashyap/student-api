import { StudentResponse } from '../model/StudentResponse.model';
import { CourseService } from './../services/course.service';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ApiError } from './../model/ApiError.model';
import { Course } from './../model/course.model';
import { CourseSearchService } from './../services/courseSearch.service';
import { Component, ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-update-course',
  templateUrl: './updateCourse.html'
})
export class UpdateCourseComponent {
  isCourseDataAvaialable: boolean;
  courseId: string;
  courseName: string;
  instructor: string;
  courseDescription: string;


  constructor(private courseService: CourseService, private toastr: ToastsManager, private vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);
  }



  getCourseData(values: any) {

    this.courseService.getCourseById(values.courseId)
      .subscribe((data: Course) => {
        if (data != null) {
          const course = new Course(data);
          this.isCourseDataAvaialable = true;
          this.courseId = course.courseId;
          this.courseName = course.courseName;
          this.courseDescription = course.courseDescription;
          this.instructor = course.instructor;


        }
      },
      (error: ApiError) => {
        if (error != null) {
          const apiError = new ApiError(error);
          this.isCourseDataAvaialable = false;
          this.toastr.error(apiError.getMessage());

        } else {
          this.toastr.error('Something went wrong.Please try after sommetime..');

        }

      });

  }

  updateCourse(values: any) {
    console.log('Course = ' + values);
    const course = new Course(values);
    this.courseService.updateCourse(course)
      .subscribe((data: StudentResponse) => {
        if (data != null) {
          this.toastr.success(data.getMessage());
        }
      },
      (error: ApiError) => {
        if (error != null) {
          const apiError = new ApiError(error);
          this.toastr.error(apiError.getMessage());
        } else {
          this.toastr.error('Something went wrong.Please try after sommetime..');


        }
      }
      );

  }
}
