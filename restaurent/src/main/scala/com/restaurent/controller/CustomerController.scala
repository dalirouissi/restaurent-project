package com.restaurent.controller

import java.lang.Iterable

import com.restaurent.payload.CustomerResponse
import com.restaurent.service.CustomerService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api"))
@CrossOrigin(origins = Array("http://localhost:4200"))
class CustomerController(private val customerService: CustomerService) {


  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = Array("/customers"))
  def allCustomers(): Iterable[CustomerResponse] = {
    customerService.allCustomers()
  }


}
