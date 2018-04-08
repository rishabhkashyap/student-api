import { ApiError } from './../model/ApiError.model';
import { StudentResponse } from '../model/StudentResponse.model';

import { jsonpCallbackContext } from '@angular/common/http/src/module';
import { serverDetails } from './../shared/server.details';
import { Course } from './../model/course.model';
import { Headers, Http, URLSearchParams, RequestOptions, Response } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class CourseService {
  CREATE_COURSE_URL = 'course/create';
  constructor(private http: Http) { }

  public addCourse(course: Course): Observable<any> {
    const header = new Headers();
    header.append('Content-Type', 'application/json');
    const options = new RequestOptions({ headers: header });
     return this.http.post(serverDetails.baseURL + this.CREATE_COURSE_URL, course, options)
              .map(response => {
                return new StudentResponse(response.json());
              })
              .catch( response  => {
                      return Observable.throw(JSON.parse(response._body).apierror);
              });

  }

}
