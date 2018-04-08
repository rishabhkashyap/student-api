
export class Course {
  courseId: number;
  courseName: string;
  courseDescription: string;
  instructor: string;

constructor(values: Object = {}) {
    Object.assign(this, values);
  }


}
