package com.restaurent.controller

import java.lang.{Iterable, Long}

import com.restaurent.payload.{OrderJson, OrderRequest, OrderResponse}
import com.restaurent.service.OrderService
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{CrossOrigin, DeleteMapping, GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api"))
@CrossOrigin(origins = Array("http://localhost:4200"))
class OrderController(private val orderService: OrderService) {

  @GetMapping(path = Array("/order/{orderId}"))
  def order(@PathVariable orderId: Long): OrderResponse = {
    orderService.findById(orderId)
  }

  @GetMapping(path = Array("/order"))
  def orders():Iterable[OrderJson] = {
    orderService.allOrders()
  }

  @PostMapping(path = Array("/order"))
  def createOrder(@RequestBody orderRequest: OrderRequest):Unit = {
    orderService.createOrder(orderRequest)
  }

  @DeleteMapping(path = Array("/order/{orderId}"))
  def delete(@PathVariable orderId: Long): Unit = {
    val deleted = orderService.deleteOrder(orderId)
    if(deleted) {
      ResponseEntity.accepted().build()
    } else {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
  }

}
