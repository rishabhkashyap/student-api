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
     private vcr: ViewContainerRef) {

    this.bsConfig = Object.assign({}, { containerClass: 'theme-dark-blue' });
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

    if ( form.value.dob !== undefined && form.value.dob !== null) {
      const  dt: Date = form.value.dob;
      if ( form.value.dob.length > 10) {
        const dtStrArr: any = dt.toLocaleDateString().split('/');
        form.value.dob = dtStrArr[2] + '-' + dtStrArr[0] + '-' + dtStrArr[1];
      }



     // form.value.dob = dt.format();
     console.log('dt = ' + form.value.dob.length);
    }

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
