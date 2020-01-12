package com.restaurent.entity

import java.math.BigDecimal
import java.util.{ArrayList, List}

import javax.persistence.{CascadeType, Column, Entity, GeneratedValue, GenerationType, Id, JoinColumn, ManyToOne, OneToMany, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "customerorder")
class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @Column(name = "orderNo")
  var orderNo: String = _

  @BeanProperty
  @JoinColumn(name = "customerId")
  @ManyToOne
  var customer : Customer = _

  @BeanProperty
  @Column(name="pmethod")
  var pMethod: String = _

  @BeanProperty
  @Column(name = "gtotal")
  var gTotal: BigDecimal = _

  @BeanProperty
  @OneToMany(mappedBy = "order", cascade = Array(CascadeType.ALL))
  var orderItems: List[OrderItem] = new ArrayList[OrderItem]

}
