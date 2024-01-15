import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {InventoryService} from "../services/inventory.service";
import {DialogRef} from "@angular/cdk/dialog";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-inventory-add-edit',
  templateUrl: './inventory-add-edit.component.html',
  styleUrls: ['./inventory-add-edit.component.scss']
})
export class InventoryAddEditComponent implements OnInit {

  inventoryForm: FormGroup;

  item_type: string[] = [
    'Television',
    'Radio',
    'Washing Machine',
    'LapTop'
  ];

  item_brand: string[] = [
    'Samsung',
    'LG',
    'Apple',
  ];

  constructor(
    private _fb: FormBuilder,
    private _inventoryService: InventoryService,
    private _dialogRef: MatDialogRef<InventoryAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.inventoryForm = this._fb.group({
      itemName: '',
      itemBrand: '',
      itemDescription: '',
      itemPrice: '',
      itemExdate: ''
    })
  }

  ngOnInit() {
    this.inventoryForm.patchValue(this.data)
  }

  onFormSubmit() {

    if (this.inventoryForm.valid) {
      //console.log(this.data);
        if (this.data) {
          this._inventoryService.updateInventory(this.data.itemId,this.inventoryForm.value).subscribe({
            next: (val: any) => {
              alert('Inventory Updated successfully');
              this._dialogRef.close(true);
            },
            error: (err:any) => {
              console.error(err);
            }
          })
          //console.log(this.data);
        }else {
          console.log(this.inventoryForm.value);
          this._inventoryService.addInventory(this.inventoryForm.value)
            .subscribe({
              next: (val: any) => {
                alert('Inventory added successfully');
                this._dialogRef.close(true);
              },
              error: (err) => {
                console.error(err);
              }
            })
        }

      }


  }
}

