import { jsonpCallbackContext } from '@angular/common/http/src/module';
import { serverDetails } from './../shared/server.details';
import { Course } from './../model/course.model';
import { Headers, Http, URLSearchParams, RequestOptions , Response} from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CourseSearchService {

  constructor(private http: Http) { }

  public getCourseById(courseId: string): Observable<Course> {
    const header = new Headers();
    header.append('Content-Type', 'application/json');
    const options = new RequestOptions({ headers: header });
    return this.http.get(serverDetails.baseURL + '/course/' + courseId, options)
             .map(response => {
               return new Course(response.json());
              })
              .catch(this.handleError);





  }
  private extractData(res: Response) {
    const body = res.json();
       return body;
  }
  private handleError(error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }
}


