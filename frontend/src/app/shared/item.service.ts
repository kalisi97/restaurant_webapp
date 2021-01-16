import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Item } from './item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  formData:Item;

  constructor(public http:HttpClient) { }


  getItemById(id:number):any{
    return this.http.get(environment.apiUrl+'/items/'+id).toPromise();
      }

getItemsList()
{
 return this.http.get(environment.apiUrl+'/items').toPromise();
}

deleteItem(id : number)
{
  return this.http.delete(environment.apiUrl + '/items/'+id).toPromise();
}

save() {

  var body = {

    Price:  parseFloat(this.formData.Price.toString()),
    Name: this.formData.Name,
  }


  return this.http.post(environment.apiUrl + '/items', body);
}


update() {

  var body = {
    Price:  parseFloat(this.formData.Price.toString()),
    Name: this.formData.Name,
  }

  return this.http.put(environment.apiUrl + '/items/'+this.formData.ItemId, body);
}

}