
export class Course {
  courseId: number;
  courseName: string;
  courseDescription: string;
  instructor: string;

constructor(values: Object = {}) {
    Object.assign(this, values);
  }



  // public  getCourseId(): number {
  //   return this.courseId;
  // }

  // public setCourseId(courseId: number) {
  //   this.courseId = courseId;
  // }

  // public getCourseName(): string {
  //   return this.courseName;
  // }

  // public setCourseName(courseName: string) {
  //   this.courseName = courseName;
  // }

  // public getCourseDescription(): string {
  //   return this.courseName;
  // }

  // public setCourseDescription(courseDescription: string) {
  //   this.courseDescription = courseDescription;
  // }

  // public getInstructor(): string {
  //   return this.instructor;
  // }

  // public setInstructor(instructor: string) {
  //   this.instructor = instructor;
  // }



}
