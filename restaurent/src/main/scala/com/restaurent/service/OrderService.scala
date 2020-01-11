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
    var order = orderRepository.findOne(orderId)

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
      orderRepository.delete(orderId)
    } catch {
      case _: Throwable => print("delete operation not performed " + _)
    }
    return orderRepository.findOne(orderId) != null
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
      val customer = customerRepository.findOne(orderRequest.customerID)
      order.gTotal = orderRequest.gTotal
      order.pMethod = orderRequest.pMethod
      order.orderNo = orderRequest.orderNo
      order.setCustomer(customer)
      val orderItems = orderRequest.orderItems.stream().map(it => {
        var oi = new OrderItem
        oi.quantity = it.quantity
        val item = itemRepository.findOne(it.itemId)
        oi.setItem(item)
        oi.setOrder(order)
        oi
      }).collect(Collectors.toList[OrderItem])

      order.setOrderItems(orderItems)
      orderRepository.save(order)
    } else {
      var order =  orderRepository.findOne(orderRequest.orderId)
      order.setCustomer(customerRepository.findOne(orderRequest.customerID))
      order.setGTotal(orderRequest.getGTotal)
      order.setPMethod(orderRequest.getPMethod)
      var newItems = orderRequest.orderItems.stream()
                      .filter(item => item.getOrderItemId <= 0)
                      .map(it => {
                        var oi = new OrderItem
                        oi.quantity = it.quantity
                        val item = itemRepository.findOne(it.itemId)
                        oi.setItem(item)
                        oi.setOrder(order)
                        oi
                      })
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
      orderRepository.save(order)
      val saveOrder = orderItemRepository.delete(deletedOrderItem)
      print(saveOrder)
    }
  }

}
