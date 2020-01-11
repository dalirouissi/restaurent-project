package com.restaurent.service

import java.lang.Iterable
import java.util.ArrayList

import com.restaurent.entity.Customer
import com.restaurent.payload.CustomerResponse
import com.restaurent.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {


  def allCustomers(): Iterable[CustomerResponse] = {
    var customers = new ArrayList[CustomerResponse]
    customerRepository.findAll().forEach(customer => {
      val customerResponse = new CustomerResponse(customer.id, customer.getName)
      customers.add(customerResponse)
    })
    customers
  }

}
