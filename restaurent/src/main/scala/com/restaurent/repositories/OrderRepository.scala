package com.restaurent.repositories

import java.lang.Long

import com.restaurent.entity.Order
import org.springframework.data.repository.CrudRepository

trait OrderRepository  extends CrudRepository[Order, Long]{

}
