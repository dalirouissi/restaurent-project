package com.restaurent.payload

import scala.beans.BeanProperty

class ItemResponse {

  @BeanProperty
  var itemId: Long = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var price: BigDecimal = _
}
