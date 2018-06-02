import { ToastsManager } from 'ng2-toastr';
import { Student } from './../model/Student.model';
import { StudentSerice } from './../services/student.service';
import { countries } from './../shared/CountryData.component';
import { Component, ViewContainerRef } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { NgForm } from '@angular/forms/src/directives';
import { StudentResponse } from '../model/StudentResponse.model';
import { ApiError } from '../model/ApiError.model';
import { DatePipe } from '@angular/common' ;
import { DatepickerOptions } from 'ng2-datepicker';
import * as frLocale from 'date-fns/locale/fr';


@Component({
  selector: 'app-add-stu',
  templateUrl: './addStudent.html'
})
export class AddStudentComponent {
  addressCountries = countries;
  addressStates = [];
  isGenderValid: boolean;
  enableStates: boolean;
  bsConfig: Partial<BsDatepickerConfig>;
  gender: string;
  country: string;
  state: string;
  student: Student;
  apiError: ApiError;

  constructor(private studentService: StudentSerice, private toastr: ToastsManager,
     private vcr: ViewContainerRef ) {

    this.bsConfig = Object.assign(
      {},
      { containerClass: 'theme-dark-blue',
      showWeekNumbers: false,
      dateInputFormat: 'YYYY-MM-DD'
       });
    this.gender = 'defaultVal';
    this.country = 'defaultVal';
    this.toastr.setRootViewContainerRef(vcr);





  }

  populateStates(value: any) {
    const selectedCountryStates = this.addressCountries.find(ele => ele.country === value).states;
    if (selectedCountryStates != null) {
      this.enableStates = true;
      this.addressStates = selectedCountryStates;
      this.state = this.addressStates[0];
    } else {
      this.addressStates = null;
      this.enableStates = false;
    }
  }

  validateGender(value: any) {
    console.log('Value = ' + value);
    if (this.gender === 'Select a gender' ) {
      this.isGenderValid = true;

    }


  }

  onSubmit(form: NgForm) {
      //let dtStr: string = form.value.dob;
      //const pos: number = dtStr.indexOf('-', 0 );
    // if ( form.value.dob !== undefined && form.value.dob !== null && pos === -1) {
    //   const  dt: Date = form.value.dob;

    //     const dtStrArr: string[] = dt.toLocaleDateString().split('/');
    //     const day = parseInt(dtStrArr[1] , 10);
    //     if (day < 10  ) {
    //       dtStrArr[1] = '0' + dtStrArr[1];
    //     }
    //     if ( dt.getMonth() < 9 ) {
    //       dtStrArr[0] = '0' + dtStrArr[0];

    //     }
    //     form.value.dob = dtStrArr[2] + '-' + dtStrArr[0] + '-' + dtStrArr[1];




    //  // form.value.dob = dt.format();
    //  console.log('dt = ' + form.value.dob.length);
    // }
    const datePipe = new DatePipe('en-US');
    form.value.dob = datePipe.transform(form.value.dob,'yyyy-MM-dd');

    if ( form.value.addressLine2 === undefined) {
      form.value.addressLine2 = '';

    }

    this.student = new Student(form.value);
    console.log('student = ' + JSON.stringify(this.student) );
    this.studentService.createStudentRecord(this.student)
    .subscribe((data: StudentResponse ) => {
      if ( data !== null) {
        this.toastr.success(data.getMessage());
      } else {
        this.toastr.error('Something went wrong');
      }
    },
    (data: ApiError) => {
      if ( data !== null ) {
        this.apiError = new ApiError(data);

        this.toastr.error(this.apiError.getMessage());
      }
    }
  );

    console.log( form.value );
  }
  onSelect(selectedState: any) {
    this.state = selectedState;
  }

}
