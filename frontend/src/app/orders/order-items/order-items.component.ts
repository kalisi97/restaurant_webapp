import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { OrderItem } from 'src/app/shared/order-item.model';
import { ItemService } from 'src/app/shared/item.service';
import { Item } from 'src/app/shared/item.model';
import { OrderService } from 'src/app/shared/customerorder.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: []
})

export class OrderItemsComponent implements OnInit {
  
  formData: OrderItem;
  itemList: Item[];
  isValid: boolean = true;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data,
    public dialogRef: MatDialogRef<OrderItemsComponent>,
    public ItemService: ItemService,
    public orderService: OrderService) { }

  ngOnInit(): void {
    this.ItemService.getItemsList().then(res => this.itemList = res as Item[]);

        this.formData = {
          OrderItemId: null,
          OrderId: this.data.OrderId,
          ItemId: 0,
          ItemName: '',
          Price: 0,
          Quantity: 0,
          Total: 0
        } 

  }

  updatePrice(ctrl) {
    if (ctrl.selectedIndex == 0) {
      this.formData.Price = 0;
      this.formData.ItemName = '';
    }
    else {
      this.formData.Price = this.itemList[ctrl.selectedIndex - 1].Price;
      this.formData.ItemName = this.itemList[ctrl.selectedIndex - 1].Name;
    }
    this.updateTotal();
  }

  updateTotal() {
    this.formData.Total = parseFloat((this.formData.Quantity * this.formData.Price).toFixed(2));
  }

  onSubmit(form: NgForm) {
    if(this.validateForm(form.value)){
         if (this.data.orderItemIndex == null)
           this.orderService.orderItems.push(form.value);
         else
           this.orderService.orderItems[this.data.orderItemIndex] = form.value;
         this.dialogRef.close();
    }
     }


  validateForm(formData: OrderItem) {
    this.isValid = true;
    if (formData.ItemId == 0)
      this.isValid = false;
    else if (formData.Quantity == 0)
      this.isValid = false;
    return this.isValid;
  }


}
