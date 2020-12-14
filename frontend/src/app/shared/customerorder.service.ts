import { Injectable } from '@angular/core';
import { Order } from './customerorder.model';
import { OrderItem } from './order-item.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { OrderItemPOST } from './order-itemPOST.model';




@Injectable({
  providedIn: 'root'
})
export class OrderService {

formData: Order;
orderItems: OrderItem[];
orderItemsPOST: OrderItemPOST[];
DeletedOrderItemIds: string;
RemoveItems: string[];

  constructor(public http: HttpClient) {this.orderItemsPOST = [];}

 saveOrder() {
    var body = {

      CustomerId:  parseInt(this.formData.CustomerId.toString()),
      PMethod: this.formData.PMethod,
      GTotal: this.formData.GTotal,
      OrderItems: this.createOrderItemsList(this.orderItems)
    };
    return this.http.post(environment.apiUrl + '/orders', body);
  }


  createOrderItemsList(orderItems: OrderItem[])
  {  
    this.DeletedOrderItemIds = this.formData.DeletedOrderItemIds;
    this.RemoveItems = this.formData.DeletedOrderItemIds.split(',');
    for(let i = 0; i < this.orderItems.length; i++){
        if(!this.RemoveItems.includes(this.orderItems[i].ItemId.toString()))
        {
         this.orderItemsPOST.push({ItemId : parseInt(this.orderItems[i].ItemId.toString()),
           Quantity: parseInt(this.orderItems[i].Quantity.toString())});
         }
      }

      return this.orderItemsPOST;
  }


}

