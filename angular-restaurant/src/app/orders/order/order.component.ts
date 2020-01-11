import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/shared/order.service';
import { NgForm } from '@angular/forms';
import { from } from 'rxjs';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { OrderItemsComponent } from '../order-items/order-items.component';
import { CustomerService } from 'src/app/shared/customer.service';
import { Customer } from 'src/app/shared/customer.model';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styles: []
})
export class OrderComponent implements OnInit {

  customerList: Customer[];
  isValid: boolean = true;

  constructor(private service: OrderService, private customerService: CustomerService, private dialog: MatDialog,
          private router: Router, private currentRoute: ActivatedRoute) { }

  ngOnInit() {
    let orderId = this.currentRoute.snapshot.paramMap.get('id');
    if(orderId == null) {
      this.resetForm();
    } else {
      this.service.getOrderById(parseInt(orderId)).then(response =>{
          this.service.formData = response.order;
          this.service.orderItems = response.orderItems;
        });
    }

    this.customerService.getCustomerList().then(response => this.customerList = response as Customer[]);
  }

  resetForm(form?: NgForm) {
    if(form != null) 
      form.resetForm();
    this.service.formData = {
      orderId: null,
      orderNo: Math.floor(100000 + Math.random()* 900000).toString(),
      customerID: 0,
      pMethod: '',
      gTotal: 0,
      deletedOrderItemIds: ''
    };
    this.service.orderItems=[];
  }

  AddOrEditOrderItem(orderItemIndex, orderId){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";
    dialogConfig.data = {orderItemIndex, orderId};
    this.dialog.open(OrderItemsComponent, dialogConfig).afterClosed().subscribe(res => {
      this.updateGrandTotal();
    });
  }

  onDeleteOrderItem(orderItemId: number, i: number) {
    if(orderItemId != null) {
      this.service.formData.deletedOrderItemIds += orderItemId + ',';
    }
    this.service.orderItems.splice(i, 1);
    this.updateGrandTotal();
  }

  updateGrandTotal() {
    this.service.formData.gTotal = this.service.orderItems.reduce((prev, current) => {
      return prev + current.total
    }, 0);
    this.service.formData.gTotal = parseFloat(this.service.formData.gTotal.toFixed(2));
  }

  validateForm() {
    this.isValid = true;
    if(this.service.formData.customerID == 0) {
      console.log('Customer not valid  ' + this.service.formData.customerID)  
      this.isValid = false;
    } else if(this.service.orderItems.length == 0) {
      console.log('orderItem not valid')  
      this.isValid = false;
    }
    return this.isValid;
  }

  onSubmit(form: NgForm) {
    console.log('Call submit ORDER =====>  ')
    if(this.validateForm()) { 
      console.log('Call submit ORDER =====>  ')
      this.service.saveOrUpdateOrder().subscribe(res => {
        this.resetForm();
        this.router.navigate(['/orders']);
      });
    } 
  }
}