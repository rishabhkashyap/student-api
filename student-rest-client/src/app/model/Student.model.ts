import { EnrolledCourses } from './EnrolledCourse.model';

export class Student {
  firstname: string;
  lastname: string;
  emailId: string;
  gender: string;
  dob: string;
  mobileNo: string;
  enrolledCourse: EnrolledCourses[];
  addressLine1: string;
  adressLine2: string;
  city: string;
  state: string;
  country: string;
  pincode: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }


}
