import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SharedService} from "../services/shared.service";
import {DialogRef} from "@angular/cdk/dialog";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

  editProfileForm: FormGroup;
  logedUserId: number;

  constructor(
    private _fb: FormBuilder,
    private _userService: UserService,
    private _sharedService: SharedService,
    private _dialogRef: DialogRef<EditProfileComponent>
  ) {
    this.logedUserId = this._sharedService.getUserId();

    this.editProfileForm = this._fb.group({
      userName: '',
      userPassword: ''
    })
  }

  ngOnInit() {
    this.logedUserId = this._sharedService.getUserId();
  }

  onFormSubmit() {
    if (this.editProfileForm.valid) {

      this._userService.editUser(this.editProfileForm.value,this.logedUserId).subscribe({
        next: (val: any) => {
          alert('User Updated successfully');
          this._dialogRef.close()
           //console.log(val);
          // console.log(this.editProfileForm.value);
        },
        error: (err: any) => {
          console.error(err);
        }
      })


    }
  }

}
