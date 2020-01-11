package com.restaurent.service

import java.lang.Iterable
import java.util.ArrayList

import com.restaurent.payload.ItemResponse
import com.restaurent.repositories.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemService(@Autowired private val itemRepository: ItemRepository) {

  def listItems:Iterable[ItemResponse] = {
    var list = new ArrayList[ItemResponse]
    var items = itemRepository.findAll()
    items.forEach(item => {
      var res = new ItemResponse
      res.itemId = item.id
      res.name = item.name
      res.price = item.price
      list.add(res)
    })
    list
  }
}
