import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }


   getItemList(){
     console.log('call api to get the list of items');
     return this.http.get(environment.apiUrl + '/item').toPromise();
   }


}
