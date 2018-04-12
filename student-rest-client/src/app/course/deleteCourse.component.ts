import { ToastsManager } from 'ng2-toastr';
import { ApiError } from './../model/ApiError.model';
import { Course } from './../model/course.model';
import { CourseService } from './../services/course.service';
import { Component, OnInit, ViewContainerRef } from '@angular/core';




@Component({
  selector: 'app-delete-course',
  templateUrl: './deleteCourse.html'
})
export class DeleteCourseComponent implements OnInit {
  courses: Course[];
  display = 'none';
  courseId: string;
  constructor(private courseService: CourseService, private toastr: ToastsManager,
    private vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);

  }

  ngOnInit(): void {
    this.courseService.getAllCourses()
      .subscribe(
      (data: Course[]) => {
        this.courses = data;
        console.log('data = ' + JSON.stringify(data));
      }, (error: ApiError) => {
        if (error != null) {
          this.toastr.error(error.getMessage());
        } else {
          this.toastr.error('Something wen wrong !!');
        }
      }
      );

  }
  onDeleteCourse() {
    console.log('course id = ' + this.courseId);

  }



  onCloseHandled() {
    this.display = 'none';

  }

  openModal(courseId: string) {
    this.display = 'block';
    this.courseId = courseId;
    //console.log('course id = ' + courseId);
  }


}
