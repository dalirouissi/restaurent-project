package com.restaurent.payload

import scala.beans.BeanProperty

class OrderItemJson {

  @BeanProperty
  var orderItemId: Long = _
  @BeanProperty
  var orderId: Long = _
  @BeanProperty
  var itemId: Long = _
  @BeanProperty
  var quantity: Int = _
  @BeanProperty
  var itemName: String = _
  @BeanProperty
  var price: BigDecimal = _
  @BeanProperty
  var total: BigDecimal = _
}
