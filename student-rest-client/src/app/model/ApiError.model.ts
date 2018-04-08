export class ApiError {
  private timeStamp: string;
  private httpStatus: string;
  private message: string;
  private debugMessage: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

  public getTimeStamp(): string {
    return this.timeStamp;
  }

  public getHttpStatus(): string {
    return this.httpStatus;
  }

  public  getMessage(): string {
    return this.message;
  }

  public getDebugMessage(): string {
    return this.debugMessage;
  }



}
