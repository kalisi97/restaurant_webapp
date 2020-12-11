import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdersComponent } from './orders/orders.component';
import { OrderComponent } from './orders/customerorder/customerorder.component';


const routes: Routes = [
 {path:'',redirectTo:'customerorder',pathMatch: 'full'},
{path:'orders',component:OrdersComponent},
{path:'customerorder', children:[
  {path:'',component:OrderComponent}
 // {path:'edit/:id',component:OrderComponent}
]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }