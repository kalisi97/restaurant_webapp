import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdersComponent } from './orders/orders.component';
import { OrderComponent } from './orders/customerorder/customerorder.component';
import { ItemsComponent } from './items/items.component';
import { HomeComponent } from './home/home.component';
import { ItemComponent } from './items/item/item.component';
import { Item } from './shared/item.model';
import { CustomersComponent } from './customers/customers.component';
import { CustomerComponent } from './customers/customer/customer.component';


const routes: Routes = [
  {path:'',redirectTo:'customerorder',pathMatch: 'full'},
  {path:'orders',component:OrdersComponent},
  {path:'customerorder', children:[
  {path:'',component:OrderComponent}]},
  {path:'items',component:ItemsComponent},
  {path:'item', children:[
    {path:'',component:ItemComponent},
    {path:':id',component:ItemComponent}
  ]},
  {path:'customers',component:CustomersComponent},
  {path:'customer', children:[
    {path:'',component:CustomerComponent},
    {path:':id',component:CustomerComponent}
  ]}
 ];
 
 @NgModule({
   imports: [RouterModule.forRoot(routes)],
   exports: [RouterModule]
 })
 export class AppRoutingModule { }