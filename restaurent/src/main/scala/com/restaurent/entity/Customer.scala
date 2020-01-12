package com.restaurent.entity

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "customer")
class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @BeanProperty
  @Column(name = "name")
  var name: String = _


}
