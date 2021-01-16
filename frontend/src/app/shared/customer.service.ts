import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Customer } from './customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  
  formData:Customer;

  constructor(public http:HttpClient) { }


  getCustomerById(id:number):any{
    return this.http.get(environment.apiUrl+'/customers/'+id).toPromise();
      }

  getCustomerList()
  {
    return this.http.get(environment.apiUrl+'/customers').toPromise();
  }

  delete(id : number)
{
  return this.http.delete(environment.apiUrl + '/customers/'+id).toPromise();
}

save() {

  var body = {

    Contact:  this.formData.Contact,
    Name: this.formData.Name,
  }


  return this.http.post(environment.apiUrl + '/customers', body);
}


update() {

  var body = {
    Contact:  this.formData.Contact,
    Name: this.formData.Name,
  }

  return this.http.put(environment.apiUrl + '/customers/'+this.formData.CustomerId, body);
}
}