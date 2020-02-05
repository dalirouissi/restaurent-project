package com.restaurent.controller

import java.lang.Iterable
import java.security.Principal

import com.restaurent.payload.CustomerResponse
import com.restaurent.service.CustomerService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api"))
@CrossOrigin(origins = Array("http://localhost:4200"))
class CustomerController(private val customerService: CustomerService) {

  @GetMapping(path = Array("/customers"))
  @PreAuthorize("hasRole('USER')")
  def allCustomers(principal: Principal): Iterable[CustomerResponse] = {
    println("The principal  is  ==>   " + principal.getName + " is executing the allCustomers method")
    customerService.allCustomers()
  }
}
