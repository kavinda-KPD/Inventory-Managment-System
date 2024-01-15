import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private _http: HttpClient
  ) { }

    addUser(data:any):Observable<any> {
        return this._http.post(`http://localhost:8083/api/v1/users/save`,data);
    }

    loginUser(data:any) {
        return this._http.post(`http://localhost:8083/api/v1/users/login`,data);
    }

    editUser(data:any,userId:number) {
      return this._http.put(`http://localhost:8083/api/v1/users/update/${userId}`,data);
    }
}
