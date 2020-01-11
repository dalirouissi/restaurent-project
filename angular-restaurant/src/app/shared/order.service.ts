import { Injectable } from '@angular/core';
import { Order } from './order.model';
import { OrderItem } from './order-item.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  formData: Order;
  orderItems: OrderItem[];

  constructor(private http: HttpClient) {}

  saveOrUpdateOrder() {
    var body = {
      ...this.formData,
      orderItems: this.orderItems
    }
    console.log('POSTING  ORDER =====>  ')
    return this.http.post(environment.apiUrl + '/order', body);
  }

  getAllOrders() {
    return this.http.get(environment.apiUrl + '/order').toPromise()
  }

  getOrderById(orderId: number):any {
    return this.http.get(environment.apiUrl + '/order/' + orderId).toPromise()
  }

  deleteOrder(orderId: number) {
    return this.http.delete(environment.apiUrl + '/order/' + orderId).toPromise();
  }

}
