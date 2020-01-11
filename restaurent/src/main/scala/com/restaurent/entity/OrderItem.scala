package com.restaurent.entity

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, JoinColumn, ManyToOne, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "orderItem")
class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id:Long = _

  @BeanProperty
  @JoinColumn(name = "orderId")
  @ManyToOne
  var order: Order = _

  @BeanProperty
  @JoinColumn(name = "itemId")
  @ManyToOne
  var item: Item = _

  @BeanProperty
  @Column(name = "quantity")
  var quantity: Int = _
}
