import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomerService } from '../shared/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: []
})
export class CustomersComponent implements OnInit {

  customersList;

  term: string;
    constructor(public customerService:CustomerService,
      private router: Router,
      private toastr: ToastrService) { }
  
    ngOnInit(): void {
      this.refreshList();
    }
  
    openForEdit(customerId: number){
      this.router.navigate(['/customer/' + customerId]);
    }
  
    refreshList() {
      
    this.customerService.getCustomerList().then(res => this.customersList = res);
    }
  
    onCustomerDelete(id: number) {
      if (confirm('Are you sure you want to delete this customer?')) {
        this.customerService.delete(id).then(res => {
         this.refreshList();
          this.toastr.warning("Customer deleted successfully!");
        });
      }
    }

}
