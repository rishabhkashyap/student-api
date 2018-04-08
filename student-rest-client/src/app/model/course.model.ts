
export class Course {
  courseId: string;
  courseName: string;
  courseDescription: string;
  instructor: string;

constructor(values: Object = {}) {
    Object.assign(this, values);
  }


}
