package com.restaurent.repositories

import java.lang.Long

import com.restaurent.entity.Users
import org.springframework.data.repository.CrudRepository

trait UserRepository extends CrudRepository[Users, Long]{

  def findUserByUsername(username: String): Users
}
