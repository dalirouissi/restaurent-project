package com.restaurent.config

import javax.servlet.FilterChain
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.web.filter.OncePerRequestFilter
import io.jsonwebtoken._
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.util.Arrays


class JWTAuthorizationFilter extends OncePerRequestFilter {

  private val secretKey = "926D96C90030DD58429D2751AC1BDBBC"
  private val bearerPrefix = "Bearer "

  override def doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain): Unit = {
    response.addHeader("Access-Control-Allow-Origin", "*")
    response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization")
    response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization")
    if(request.getMethod.eq("OPTIONS")){
      response.setStatus(HttpServletResponse.SC_OK)
    } else if(request.getRequestURI.eq("/login")) {
      filterChain.doFilter(request, response)
      return
    } else {
      val jwtToken = request.getHeader("Authorization")
      print(s"The Token  ===>  ${jwtToken}")
      if(jwtToken == null || !jwtToken.startsWith(bearerPrefix)) {
        filterChain.doFilter(request, response)
          return
      }
      val token = jwtToken.substring(bearerPrefix.length)
      if(validateToken(token)) {
        val claims = extractClaims(token)
        val userId = claims.getSubject
        print(s"The user  ===>  ${userId} ")
        val role = new SimpleGrantedAuthority("ROLE_ADMIN")
        val authorities = Arrays.asList(role)
        val user: UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("dali", null, authorities)
        SecurityContextHolder.getContext.setAuthentication(user)
      }
    }
    filterChain.doFilter(request, response)
  }

  def extractClaims(token: String): Claims = {
    Jwts.parser.setSigningKey(secretKey).parseClaimsJws(token).getBody
  }

   def validateToken(authToken: String): Boolean = {
    try {
      val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken)
      println(s"The claims  ===>  ${claims}")
      return true
    } catch {
      case ex: SignatureException       => print("Invalid JWT signature")
      case ex: MalformedJwtException    => print("Invalid JWT token")
      case ex: ExpiredJwtException      => print("Expired JWT token")
      case ex: UnsupportedJwtException  => print("Unsupported JWT token")
      case ex: IllegalArgumentException => print("JWT claims string is empty")
    }
     false
   }
}

