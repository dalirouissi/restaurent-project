package com.restaurent.payload

import java.math.BigDecimal

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

class OrderJson {

  @BeanProperty
  @JsonProperty
  var orderId: Long = _

  @BeanProperty
  @JsonProperty
  var orderNo: String = _

  @BeanProperty
  var customer: String = _

  @BeanProperty
  var customerID: Long = _

  //@BeanProperty
  @JsonProperty
  var pMethod: String = _

  //@BeanProperty
  @JsonProperty
  var gTotal: BigDecimal = _
}
