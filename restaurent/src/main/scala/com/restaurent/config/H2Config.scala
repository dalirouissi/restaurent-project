package com.restaurent.config

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Configuration

@Configuration
class H2Config {

  def h2servletRegistration(): ServletRegistrationBean = {
    val registrationBean = new ServletRegistrationBean(new WebServlet)
    registrationBean.addUrlMappings("/console/*")
    registrationBean
  }
}
