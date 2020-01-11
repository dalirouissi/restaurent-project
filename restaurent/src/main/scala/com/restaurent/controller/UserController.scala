package com.restaurent.controller

import java.lang.Iterable

import com.restaurent.entity.Users
import com.restaurent.service.UserService
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api"))
class UserController(@Autowired private val userService: UserService, @Autowired private val dataSource: DataSource) {

  @GetMapping(path = Array("/users"))
  def getAllUsers(): Iterable[Users] = {
    userService.listUsers()
  }

  @GetMapping(path = Array("/users/{id}"))
  def getUser(@PathVariable id: Long): Users = {
    userService.findUser(id)
  }

  @PostMapping(path = Array("/users"))
  def create(@RequestBody user: Users): ResponseEntity[Long] = {
    val id = userService.create(user)
    ResponseEntity.status(HttpStatus.CREATED).body(id)
  }
}
