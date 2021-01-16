import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrdersComponent } from './orders/orders.component';
import { OrderComponent } from './orders/customerorder/customerorder.component';
import { OrderItemsComponent } from './orders/order-items/order-items.component';
import { OrderService } from './shared/customerorder.service';
import { MatDialogModule} from '@angular/material/dialog';
import { HttpClientModule} from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { ItemsComponent } from './items/items.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { ItemComponent } from './items/item/item.component';
import { CustomersComponent } from './customers/customers.component';
import { CustomerComponent } from './customers/customer/customer.component';
@NgModule({
  declarations: [
    AppComponent,
    OrdersComponent,
    OrderComponent,
    OrderItemsComponent,
    ItemsComponent,
    HomeComponent,
    ItemComponent,
    CustomersComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    Ng2SearchPipeModule
  ],
  entryComponents: [OrderItemsComponent],
  providers: [OrderService],
  bootstrap: [AppComponent],

  exports: [RouterModule]
})
export class AppModule { }
