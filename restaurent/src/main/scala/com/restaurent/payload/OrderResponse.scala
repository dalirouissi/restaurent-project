package com.restaurent.payload

import java.util.{List, ArrayList}

import scala.beans.BeanProperty

class OrderResponse {

  @BeanProperty
  var order: OrderJson = _

  @BeanProperty
  var orderItems: List[OrderItemJson] = new ArrayList
}
