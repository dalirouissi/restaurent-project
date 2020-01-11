import { Component, OnInit } from '@angular/core';
import { OrderService } from '../shared/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styles: []
})
export class OrdersComponent implements OnInit {

  orderList;

  constructor(private service: OrderService, private router: Router) { }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.service.getAllOrders().then(res => {
      this.orderList = res;
  });
  }

  openForEdit(orderId: number) {
    console.log('Open for edit   ' + orderId);
    this.router.navigate(['/order/edit/' + orderId]);
  }

  onOrderDelete(orderId: number) {
    console.log('Delete order with id ' + orderId);
    this.service.deleteOrder(orderId).then(resp => {
        this.refresh();
  });  
  }
}
