import { Component, OnInit } from '@angular/core';
import { OrderService } from './../../shared/customerorder.service';
import { NgForm } from '@angular/forms';
import { OrderItemsComponent } from '../order-items/order-items.component';
import { CustomerService } from 'src/app/shared/customer.service';
import { Customer} from 'src/app/shared/customer.model';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogConfig} from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-order',
  templateUrl: './customerorder.component.html',
  styleUrls: []
})


export class OrderComponent implements OnInit {

  customerList : Customer[]
  isValid: boolean = true;

  constructor(public service:OrderService,
    public customerService:CustomerService,
    public dialog: MatDialog,
    public router: Router,
    public toastr: ToastrService,
    private currentRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.resetForm();
    this.customerService.getCustomerList().then(res => this.customerList = res as Customer[]);
  }

  resetForm(form?:NgForm){
    if(form=null)
    form.resetForm();
    this.service.formData = {
      OrderId: null,
      CustomerId: 0,
      PMethod: '',
      GTotal: 0,
      DeletedOrderItemIds: '',
      Name: ''
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

        this.dialog.open(OrderItemsComponent, dialogConfig).afterClosed().subscribe(res=>
          {
            this.updateGrandTotal();
          });
    
  }

  onDeleteOrderItem(orderItemID:number, i: number) {
    if (orderItemID != null)
    this.service.formData.DeletedOrderItemIds += orderItemID + ",";
    this.service.orderItems.splice(i, 1);
    this.updateGrandTotal();
  }

  updateGrandTotal() {
    this.service.formData.GTotal = this.service.orderItems.reduce((prev, curr) => {
      return prev + curr.Total;
    }, 0);
    this.service.formData.GTotal = parseFloat(this.service.formData.GTotal.toFixed(2));
  }

  validateForm() {
    this.isValid = true;
    if (this.service.formData.CustomerId == 0)
      this.isValid = false;
    else if (this.service.orderItems.length == 0)
      this.isValid = false;
    return this.isValid;
  }

  onSubmit(form: NgForm) {
    if (this.validateForm()) {
      
      this.service.saveOrder().subscribe(res => {
      //  this.resetForm();
        this.toastr.success('Submitted Successfully', 'Restaurent App.');
      
       this.router.navigate(['/orders']);
        
      })
      
    }
  }


}
