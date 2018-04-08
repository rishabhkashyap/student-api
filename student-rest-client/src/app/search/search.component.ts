import { CourseSearchService } from './../services/courseSearch.service';
import { Course } from './../model/course.model';
import { Component } from '@angular/core';

@Component({
  selector: 'app-course-search',
  templateUrl: './search.component.html'
})
export  class SearchComponent {
     course: Course;
     courseName: string;
     courseId: string;

    constructor(private courseSearchService: CourseSearchService) {

    }


   getCourseData() {

    console.log('Make rest call to get data ');
        this.courseSearchService.getCourseById(this.courseId)
                 .subscribe((data: Course) => {
                   if (data != null) {

                     this.course = new Course(data);
                     this.courseName = data.courseDescription;
                    //  this.course.setCourseId(data.courseId) ;
                    //  this.course.setCourseName(data.courseName);
                    //  this.course.setInstructor( data.instructor);
                    //  this.course.setCourseDescription(data.courseDescription);
                      console.log('data name inside= ' + this.course.courseName);
                    }
                 } );




  }

}
