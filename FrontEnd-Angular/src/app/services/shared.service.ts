import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  logedUserId: number;
  constructor() {
    this.logedUserId = 0;
  }

  setUserId(data:number){
    this.logedUserId = data;
  }

  getUserId():number{
    return this.logedUserId;
  }
}
