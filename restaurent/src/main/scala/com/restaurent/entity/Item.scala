package com.restaurent.entity

import java.lang.Long
import java.math.BigDecimal

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty


@Entity
@Table(name = "item")
class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = _

  @BeanProperty
  @Column(name = "name")
  var name: String = _

  @BeanProperty
  @Column(name="price")
  var price: BigDecimal = _

}
