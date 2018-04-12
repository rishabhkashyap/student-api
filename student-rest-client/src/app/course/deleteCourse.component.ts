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
    this.courseService.deleteCourse(this.courseId)
      .subscribe((data: Course) => {
        if (data != null) {
          this.courses = this.courses.filter(ele => ele.courseId !== data.courseId);
          console.log('from  delete response    ' + JSON.stringify(this.courses));
          this.toastr.success(data.courseName + 'is deleted successfully');
        }
      },
      (data: ApiError) => {
        if (data != null) {
          this.toastr.error(data.getMessage());
        } else {
          this.toastr.error('Something went wrong!!');
        }
      });
    this.display = 'none';

  }



  onCloseHandled() {
    this.display = 'none';

  }

  openModal(courseId: string) {
    this.display = 'block';
    this.courseId = courseId;
  }


}
