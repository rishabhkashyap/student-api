import { Component } from '@angular/core';


@Component({
  selector: 'app-warning-modal',
  templateUrl: './deleteCourse.html'
})
export class DeleteWarningComponent {
  display = 'none';

  constructor(){}

  onCloseHandled() {
    this.display = 'none';

  }

  openModal() {
    this.display = 'block';
  }


}
