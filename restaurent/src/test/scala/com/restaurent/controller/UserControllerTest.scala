package com.restaurent.controller


import java.util

import com.restaurent.entity.Users
import org.apache.tomcat.util.codec.binary.Base64
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.{HttpEntity, HttpHeaders, MediaType}
import org.springframework.test.context.junit4.SpringRunner
@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @Autowired var template: TestRestTemplate = _

  @Test
  def postcreateUser() = {
    val headers = new HttpHeaders
    headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(("root" + ":" + "root").getBytes())))
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(util.Arrays.asList(MediaType.APPLICATION_JSON))
    val user = new Users
    user.id = 101
    user.setUsername("Test")
    user.setPassword("Test")
    user.setEnabled(true)
    val entity = new HttpEntity(user, headers)
    val result = template.postForObject("/api/users", entity, classOf[String])
    print(result)
  }
}
