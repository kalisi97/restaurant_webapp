import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { OrderService } from '../shared/customerorder.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: []
})
export class OrdersComponent implements OnInit {


  ordersList;
  term: string;
    constructor(public orderService:OrderService,
      private router: Router,
      private toastr: ToastrService) { }
  
    ngOnInit(): void {
      this.refreshList();
    }
  
   
    refreshList() {
    this.orderService.getOrdersList().then(res => this.ordersList = res);
    }
  
    onOrderDelete(id: number) {
      if (confirm('Are you sure you want to delete this order?')) {
        this.orderService.delete(id).then(res => {
         this.refreshList();
          this.toastr.warning("Order deleted successfully!");
        });
      }
    }

}
