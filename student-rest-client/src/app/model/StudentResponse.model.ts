

export class StudentResponse {
  private message: string;
  private httpStatus: string;

  constructor(values: Object = {}) {
      Object.assign(this , values);
  }

  public getMessage(): string {
    return this.message;
  }

  public getHttpStatus(): string {
    return this.httpStatus;
  }

}
