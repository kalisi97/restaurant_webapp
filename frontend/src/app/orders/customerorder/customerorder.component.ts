import { Component, OnInit } from '@angular/core';
import { OrderService } from './../../shared/customerorder.service';
import { NgForm } from '@angular/forms';
import { OrderItemsComponent } from '../order-items/order-items.component';
import { CustomerService } from 'src/app/shared/customer.service';
import {Customer} from 'src/app/shared/customer.model';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogConfig} from '@angular/material/dialog';


@Component({
  selector: 'app-order',
  templateUrl: './customerorder.component.html',
  styleUrls: []
})


export class OrderComponent implements OnInit {

  constructor(public service:OrderService,
    public customerService:CustomerService,
    public dialog: MatDialog,
    public router: Router,
    private currentRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.resetForm();
  }

  resetForm(form?:NgForm){
    if(form=null)
    form.resetForm();
    this.service.formData = {
      OrderId: null,
      CustomerId: 0,
      PMethod: '',
      GTotal: 0,
      DeletedOrderItemIds: ''
    };
    this.service.orderItems = [];
  }

  AddOrEditOrderItem(orderItemIndex, OrderId)
  {
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";
    dialogConfig.data = {orderItemIndex, OrderId};
    
 this.dialog.open(OrderItemsComponent, dialogConfig);
  //   this.dialog.open(OrderItemsComponent, dialogConfig).afterClosed().subscribe(res=>
     // {
     //   this.updateGrandTotal();
     // });
  }



}
