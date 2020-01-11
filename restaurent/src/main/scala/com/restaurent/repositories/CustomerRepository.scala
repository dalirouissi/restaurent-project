package com.restaurent.repositories

import java.lang.Long

import com.restaurent.entity.Customer
import org.springframework.data.repository.CrudRepository

trait CustomerRepository extends CrudRepository[Customer, Long]{

}
