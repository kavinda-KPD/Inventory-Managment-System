import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {InventoryService} from "../services/inventory.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {InventoryAddEditComponent} from "../inventory-add-edit/inventory-add-edit.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit{

   pageNo : number ;
  pageSize: number ;
  totalPages: number ;
  currentPage: number ;

  serchForm: FormGroup;

  displayedColumns: string[] = ['itemId', 'itemBrand', 'itemDescription', 'itemExdate', 'itemName', 'itemPrice', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  item_brand: string[] = [
    'Samsung',
    'LG',
    'Apple',
  ];

  constructor(
    private _fb: FormBuilder,
    private _inventoryService: InventoryService,
    private _dialog: MatDialog,
  ) {
    this.pageNo = 1;
    this.pageSize = 5;
    this.currentPage = 1;
    this.totalPages = 0;

    this.serchForm = this._fb.group({
      itemName: '',
      itemBrand: '',
      itemDescription: '',
    })
  }

  ngOnInit() {
    this.searchInventory();
  }

  searchInventory() {
    if (this.serchForm.valid) {
      //console.log(this.serchForm.value);

      this._inventoryService.searchInventory(this.serchForm.value,this.pageNo,this.pageSize).subscribe({
        next: (result: any) => {
          console.log(result);
          console.log(this.pageNo);
          console.log(this.currentPage);

          this.dataSource = new MatTableDataSource(result.items);
          this.totalPages = result.totalPages;

        },
        error: (err: any) => {
          console.error(err);
        }
      })
    }
  }

  nextPageButton() {
    if(this.currentPage<this.totalPages){
      this.currentPage = this.currentPage + 1;
      this.pageNo = this.pageNo + 1;

      this.searchInventory();
    }else {
      alert('This is the last page. No more pages');
    }
  }

  backPageButton() {
    if(1< this.currentPage){
      this.currentPage = this.currentPage - 1;
      this.pageNo = this.pageNo - 1;

      this.searchInventory();
    }else {
      alert('This is the first page. No more back pages');
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteInventory(id:number){
    this._inventoryService.deleteInventory(id).subscribe({
      next: (result:any) => {
        alert('Employee deleted');
        this.searchInventory();
      },
      error: console.log,
    });
  }

  openEditInventoryForm(data:any) {
    const dialogRef = this._dialog.open(InventoryAddEditComponent,{data});

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.searchInventory();
        }
      },
    })
  }

}
