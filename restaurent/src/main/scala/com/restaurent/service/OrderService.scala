package com.restaurent.service

import java.lang.Iterable
import java.util
import java.util.stream.Collectors

import com.restaurent.entity.{Order, OrderItem}
import com.restaurent.payload.{OrderItemJson, OrderJson, OrderRequest, OrderResponse}
import com.restaurent.repositories.{CustomerRepository, ItemRepository, OrderItemRepository, OrderRepository}
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository, private val itemRepository: ItemRepository,
                   private val customerRepository: CustomerRepository, private val orderItemRepository: OrderItemRepository) {


  def findById(orderId: Long) : OrderResponse = {
    var response = new OrderResponse
    var order = orderRepository.findById(orderId)
        .orElseThrow(() => new RuntimeException(s"order not found for the id ${orderId}"))

       order.getOrderItems.stream().forEach(item => {
          var orderItem = new OrderItemJson
          orderItem.orderItemId = item.id
          orderItem.itemId = item.item.id
          orderItem.itemName = item.item.name
          orderItem.orderId = orderId
          orderItem.price = item.item.price
          orderItem.quantity = item.quantity
          orderItem.total = BigDecimal(item.item.price.floatValue()*item.getQuantity)
          response.orderItems.add(orderItem)
        });
      response.order = orderMapper.apply(order)
    response
  }


  def allOrders():Iterable[OrderJson] = {
    var response = new util.ArrayList[OrderJson]
    val orders = orderRepository.findAll()
    orders.forEach(order => {
      response.add(orderMapper.apply(order))
    })
    response
  }

  def deleteOrder(orderId: Long): Boolean = {
    try{
      orderRepository.deleteById(orderId)
    } catch {
      case _: Throwable => print("delete operation not performed " + _)
    }
    orderRepository.findById(orderId) != null
  }

  private val orderMapper = (order: Order) => {
    var element = new OrderJson
    element.orderId = order.id
    element.customer = order.getCustomer.getName
    element.setCustomerID(order.getCustomer.id)
    element.gTotal = order.getGTotal
    element.setOrderNo(order.orderNo)
    element.pMethod = order.pMethod
    element
  }

  def createOrder(orderRequest: OrderRequest):Unit = {
    if(orderRequest.orderId == null || orderRequest.orderId <= 0) {
      var order = new Order
      val customer = customerRepository.findById(orderRequest.customerID)
                                       .orElseThrow(() => new RuntimeException(s"Curomer not Found for the id ${orderRequest.customerID}"))
      order.gTotal = orderRequest.gTotal
      order.pMethod = orderRequest.pMethod
      order.orderNo = orderRequest.orderNo
      order.setCustomer(customer)
      val orderItems = orderRequest.orderItems.stream().map(it => {
        val item = itemRepository.findById(it.itemId)
        if(item.isPresent){
          var oi = new OrderItem
          oi.quantity = it.quantity
          oi.setItem(item.get())
          oi.setOrder(order)
          oi
        } else {
            null
        }
      }).filter((it: OrderItem )=> it.item != null)
        .collect(Collectors.toList[OrderItem])

      order.setOrderItems(orderItems)
      orderRepository.save(order)
    } else {
      var order =  orderRepository.findById(orderRequest.orderId)
                                  .orElseThrow(() => new RuntimeException(s"Order not Found for the id ${orderRequest.orderId}"))
      order.setCustomer(customerRepository.findById(orderRequest.customerID).get())
      order.setGTotal(orderRequest.getGTotal)
      order.setPMethod(orderRequest.getPMethod)
      var newItems = orderRequest.orderItems.stream()
                      .filter(item => item.getOrderItemId <= 0)
                      .map(it => {
                        val item = itemRepository.findById(it.itemId)
                        if(item.isPresent) {
                          var oi = new OrderItem
                          oi.quantity = it.quantity
                          oi.setItem(item.get())
                          oi.setOrder(order)
                          oi
                        } else {
                           null
                        }
                      }).filter((it: OrderItem )=> it.item != null)
                      .collect(Collectors.toList[OrderItem])

     val itemIds = orderRequest.getOrderItems.stream()
                                .filter(orderItem => orderItem.getOrderItemId > 0)
                                .map(i => i.getOrderItemId)
                                .collect(Collectors.toList[Long])

      var deletedOrderItem = order.getOrderItems.stream()
                                                .filter(orderItem => !itemIds.contains(orderItem.id))
                                                .map(orderItem => {
                                                  orderItem.setOrder(null)
                                                  orderItem.setItem(null)
                                                  orderItem
                                                  })
                                                .collect(Collectors.toList[OrderItem])

      order.getOrderItems.addAll(newItems)
      val saveOrder = orderRepository.save(order)
      orderItemRepository.deleteAll(deletedOrderItem)
      print(saveOrder)
    }
  }

}
