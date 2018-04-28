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
  addressStates = [];
  isGenderValid: boolean;
  enableStates: boolean;
  bsConfig: Partial<BsDatepickerConfig>;
  gender: string;
  country: string;
  state: string;

  constructor() {

    this.bsConfig = Object.assign({}, { containerClass: 'theme-dark-blue' });
    this.gender = 'defaultVal';
    this.country = 'defaultVal';

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
    form.value.dob = new Date(form.value.dob).toLocaleDateString();
    if ( form.value.addressLine2 === undefined) {
      form.value.addressLine2 = '';

    }
    console.log( form.value );
    console.log('AL2 = ' + form.value.addressLine2);
  }
  onSelect(selectedState: any) {
    this.state = selectedState;
  }

}
