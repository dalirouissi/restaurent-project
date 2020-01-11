package com.restaurent.repositories

import java.lang.Long

import com.restaurent.entity.OrderItem
import org.springframework.data.repository.CrudRepository

trait OrderItemRepository extends CrudRepository[OrderItem, Long]{

}
