import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class InventoryService implements OnInit{

  // size:number = 5;
  // page:number = 1;

  constructor(
    private _http:HttpClient
  ) {

  }

  ngOnInit() {
    // this.size = 5;
    // this.page = 1;
  }

  addInventory(data:any){
    console.log(data)
    return this._http.post('http://localhost:8083/api/v1/items/save',data)
  }

  getAllInventoryList(page: number, size: number):any{

    // Create an instance of HttpParams and set the parameters
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());

    return this._http.get('http://localhost:8083/api/v1/items/get-all-items', { params });
  }

  deleteInventory(id: number):any {
    return this._http.delete(`http://localhost:8083/api/v1/items/delete-customer/${id}`);
  }

  updateInventory(id:number,data:any):any {
    return this._http.put(`http://localhost:8083/api/v1/items/update2/${id}`,data);
  }

  searchInventory(data:any,page:any,size:any):any {
    //console.log(data);

    const params = new URLSearchParams(data); // Convert the data object to query parameters
    const url = `http://localhost:8083/api/v1/items/filter-items?${params.toString()}&page=${page}&size=${size}`;

    console.log(url);
    return this._http.get(url);
  }
}


