package com.restaurent.controller

import java.lang.Iterable

import com.restaurent.payload.ItemResponse
import com.restaurent.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api"))
@CrossOrigin(origins = Array("http://localhost:4200"))
class ItemController(@Autowired private val itemService: ItemService) {

  @GetMapping(path = Array("/item"))
  def allItems(): Iterable[ItemResponse] = {
    itemService.listItems;
  }
}
