import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomerService } from 'src/app/shared/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: []
})
export class CustomerComponent implements OnInit {

 
  isValid: boolean=true;
  customersList;
  forUpdate: boolean=false;
  constructor(public dialog: MatDialog,public service: CustomerService,
    public toastr: ToastrService,
    public router:Router,
    private currentRoute: ActivatedRoute) { }

  ngOnInit(): void {
   
    let CustomerId = this.currentRoute.snapshot.paramMap.get('id');
    if(CustomerId == null)
    this.resetForm();
    else { this.service.getCustomerById(parseInt(CustomerId)).then(res => {
      this.service.formData = res;  
      this.forUpdate = true;
    });
  }
  this.service.getCustomerList().then(res => this.customersList = res);
    
}
resetForm(form?:NgForm){
  if(form=null)
  form.resetForm();
  this.service.formData={
    CustomerId: null,
    Name: "",
    Contact: ""
  }
}


onSubmit(form: NgForm) {
  if (this.validateForm()) {
      
    if(this.forUpdate == true)
    {
      this.service.update().subscribe(res => {
        this.resetForm();
        this.toastr.success("Successfully saved!");
        this.router.navigate(['customers']);
      })
    }else
    {
      this.service.save().subscribe(res => {
      this.resetForm();
      this.toastr.success("Successfully saved!");
      this.router.navigate(['customers']);
    })
    }
  
  }
}

validateForm() {
  this.isValid = true;
  if (this.service.formData.CustomerId == 0)
    this.isValid = false;
  
    if(this.service.formData.Contact == "" || this.service.formData.Name=="") this.isValid=false;
  return this.isValid;
}

}
