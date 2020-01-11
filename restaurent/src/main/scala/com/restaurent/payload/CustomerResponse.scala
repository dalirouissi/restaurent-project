package com.restaurent.payload

import scala.beans.BeanProperty

class CustomerResponse(@BeanProperty var customerID: Long, @BeanProperty var name: String) {

}
