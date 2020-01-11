package com.restaurent.payload

import java.math.BigDecimal
import java.util.List
import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

class OrderRequest {

  @BeanProperty
  @JsonProperty
  var orderId: Long = _

  @BeanProperty
  @JsonProperty
  var orderNo: String = _

  @BeanProperty
  var customerID: Long = _

  @BeanProperty
  @JsonProperty
  var pMethod: java.lang.String = _

  @BeanProperty
  @JsonProperty
  var gTotal: BigDecimal = _

  @BeanProperty
  var orderItems: List[OrderItemJson] = new ArrayList

  @BeanProperty
  @JsonProperty
  var deletedOrderItemIds: String = _
}