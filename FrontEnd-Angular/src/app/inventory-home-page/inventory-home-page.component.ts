import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {InventoryAddEditComponent} from "../inventory-add-edit/inventory-add-edit.component";
import {InventoryService} from "../services/inventory.service";
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {EditProfileComponent} from "../edit-profile/edit-profile.component";


@Component({
  selector: 'app-inventory-home-page',
  templateUrl: './inventory-home-page.component.html',
  styleUrls: ['./inventory-home-page.component.scss']
})
export class InventoryHomePageComponent implements OnInit {

  displayedColumns: string[] = ['itemId', 'itemBrand', 'itemDescription', 'itemExdate', 'itemName', 'itemPrice' ,'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private _dialog: MatDialog,
    private _inventoryService: InventoryService,
  ) {
  }

  ngOnInit() {
    this.getAllInventories();
  }
  openAddEditInventoryForm() {
    const dialogRef = this._dialog.open(InventoryAddEditComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getAllInventories();
        }
      },
    })
  }

  openEditProfile() {
    const dialogRef = this._dialog.open(EditProfileComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getAllInventories();
        }
      },
    })
  }

  openEditInventoryForm(data:any) {
    const dialogRef = this._dialog.open(InventoryAddEditComponent,{data});

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getAllInventories();
        }
      },
    })

  }

  getAllInventories() {
    const page = 1;
    const size = 100;

    this._inventoryService.getAllInventoryList(page, size).subscribe({
      next: (result: any) => {
        this.dataSource = new MatTableDataSource(result.items);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log,
    })
  }

  deleteInventory(id:number){
    this._inventoryService.deleteInventory(id).subscribe({
      next: (result:any) => {
        alert('Employee deleted');
        this.getAllInventories();
      },
      error: console.log,
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


}
