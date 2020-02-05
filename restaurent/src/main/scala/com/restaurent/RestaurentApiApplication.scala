package com.restaurent

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@EnableResourceServer
@SpringBootApplication
class RestaurentApiApplication

object RestaurentApiApplication extends App {

    SpringApplication.run(classOf[RestaurentApiApplication]);
}
