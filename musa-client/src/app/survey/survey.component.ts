import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';


@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {

  submitted = false;

  genders = ['Male', 'Female']

  occupations = ['Employee', 'Student', 'Other']

  surveyForm: FormGroup;

  constructor(private router: Router) {
    this.surveyForm = this.createFormGroup();
  }
  

  ngOnInit(): void {
  }

  createFormGroup() {
    return new FormGroup({
      personalData: new FormGroup({
        sex: new FormControl(),
        occupation: new FormControl()
       }),
      trackingChecked: new FormControl(),
      tourChecked: new FormControl(),
      requestType: new FormControl(),
      text: new FormControl()
    });
  }

  onSubmit() { 
    this.submitted = true; 
    console.log(this.surveyForm);
    if(this.surveyForm.value.tourChecked){
      this.router.navigate(['/tour']);
    } else if (this.surveyForm.value.trackingChecked){
      this.router.navigate(['/collecting']);
    } else {
      this.router.navigate(['/home']);
    }
  }
  
  revert() { this.submitted = false; }

  onTrackingCheckboxChange(){
    //console.log(event);
  }  
 
  onTourCheckboxChange(){
   //console.log(event);
  }
}
