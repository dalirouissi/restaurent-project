package com.restaurent.repositories

import java.lang.Long

import com.restaurent.entity.Item
import org.springframework.data.repository.CrudRepository

trait ItemRepository extends CrudRepository[Item, Long] {

}
