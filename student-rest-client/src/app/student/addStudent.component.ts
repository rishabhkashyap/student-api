import { countries } from './../shared/CountryData.component';
import { Component } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { NgForm } from '@angular/forms/src/directives';


@Component({
  selector: 'app-add-stu',
  templateUrl: './addStudent.html'
})
export class AddStudentComponent {
  addressCountries = countries;
  states = [];
  isGenderValid: boolean;
  enableStates: boolean;
  bsConfig: Partial<BsDatepickerConfig>;
  gender: string;
  country: string;
  state: string;

  constructor() {

    this.bsConfig = Object.assign({}, { containerClass: 'theme-dark-blue' });
    this.gender = 'defaultVal';
    this.country='defaultVal';
    this.state = 'defaultVal'

  }

  populateStates(value: any) {
    const selectedCountryStates = this.addressCountries.find(ele => ele.country === value).states;
    if (selectedCountryStates != null) {
      this.enableStates = true;
      this.states = selectedCountryStates;
    } else {
      this.states = null;
      this.enableStates = false;
    }
  }

  validateGender(value: any) {
    console.log('Value = ' + value);
    if (this.gender === 'Select a gender' ) {
      this.isGenderValid = true;
      console.log('gender = ' + this.gender);

    }

  }

  onSubmit(form: NgForm) {
    console.log(form.value);
  }

}
