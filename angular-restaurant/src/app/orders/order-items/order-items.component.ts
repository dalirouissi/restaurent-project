import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { OrderItem } from 'src/app/shared/order-item.model';
import { ItemService } from 'src/app/shared/item.service';
import { Item } from 'src/app/shared/item.model';
import { NgForm } from '@angular/forms';
import { OrderService } from 'src/app/shared/order.service';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styles: []
})
export class OrderItemsComponent implements OnInit {

  formData: OrderItem; 
  itemList: Item[];
  isValid:boolean = true;

  constructor(@Inject(MAT_DIALOG_DATA) public data, 
              public dialogRef: MatDialogRef<OrderItemsComponent>,
              private itemService: ItemService,
              private orderService: OrderService) { }

  ngOnInit() {
    this.itemService.getItemList()
    .then(response => this.itemList = response as Item[]);
    if(this.data.orderItemIndex == null) {
      this.formData ={
        orderItemId: null,
        orderId: this.data.orderId,
        itemId: 0,
        quantity: 0,
        itemName: '',
        price: 0,
        total: 0,
      }
    } else {
      this.formData = Object.assign({}, this.orderService.orderItems[this.data.orderItemIndex]);
    }

  }

  updatePrice(ctrl){
    console.log('Update Price called ....');
    if(ctrl.selectedIndex == 0) {
      this.formData.price = 0;
    } else{
      this.formData.price = this.itemList[ctrl.selectedIndex - 1].price;
      this.formData.itemName = this.itemList[ctrl.selectedIndex - 1].name;
      this.formData.orderItemId = 0;
    }
    this.updateTotal();
  }

  updateTotal(){
    console.log('Calling UPDATE TOTAL....');
    this.formData.total = parseFloat((this.formData.quantity * this.formData.price).toFixed(2));
  }

  onSubmit(form: NgForm) {
    console.log('Submit form =====> ');
    if(this.validateForm(form.value)) {
      if(this.data.orderItemIndex == null) {
        this.orderService.orderItems.push(form.value);
      } else {
        this.orderService.orderItems[this.data.orderItemIndex] = form.value;
      }
      this.dialogRef.close();
    }
  }

  validateForm(formData: OrderItem) {
    this.isValid = true;
    console.log('call validate Form itemID ==> ' + formData.itemId);
    console.log('call validate Form quantity ==>  ' + formData.quantity);
    if(formData.itemId == 0) {
      this.isValid = false;
    } else if(formData.quantity == 0) {
      this.isValid = false;
    }
    console.log('call validate Form ==> ' + this.isValid)
    //return this.isValid;  
    return true;
  } 

}
