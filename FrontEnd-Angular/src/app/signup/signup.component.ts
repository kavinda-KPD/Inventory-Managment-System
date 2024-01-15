import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit{

  public signupForm !: FormGroup;


  constructor(
    private _formBuilder:FormBuilder,
    private _userService: UserService,
    private _router: Router
  ) {
  }
  ngOnInit() {
    this.signupForm = this._formBuilder.group({
      userName: '',
      userPassword: ''
    })
  }
  onSignUp() {
    if(this.signupForm.valid){
      this._userService.addUser(this.signupForm.value).subscribe({
          next: (result:any) => {
            alert("SignUp Successfully");
            this.signupForm.reset();
            this._router.navigate(['login']);
            //console.log(result);
          },
          error: (err:any) => {
              alert("Something Wrong");
              console.error(err);
          }
      })
    }
  }
}
