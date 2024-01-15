import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {SharedService} from "../services/shared.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  public loginForm!: FormGroup;

  constructor(
      private _formBuilder: FormBuilder,
      private _userService: UserService,
      private _router: Router,
      private _sharedService: SharedService
  ) {
  }

  ngOnInit() {
    this.loginForm = this._formBuilder.group({
      userName: '',
      userPassword: ''
    })
  }

  loginUser() {
    if(this.loginForm.valid){
      this._userService.loginUser(this.loginForm.value).subscribe({
        next: (result:any) => {
          if(result.userPassword === this.loginForm.value.userPassword){
            alert(`${result.userName} you can log to the system`);
            this.loginForm.reset();
            this._router.navigate(['home']);
            this._sharedService.setUserId(result.userId);
            //console.log(result.userId);
          }else{
            alert(`${result.userName} your password is incorrect`);
          }
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
