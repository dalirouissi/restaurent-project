package com.restaurent.service

import java.lang.Iterable

import com.restaurent.entity.Users
import com.restaurent.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize}
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

//    @PreAuthorize("hasRole('admin')")
    def listUsers(): Iterable[Users] = {
      userRepository.findAll
    }

//    @PreAuthorize("hasRole('user')")
//    @PostAuthorize("returnObject.username==principal.username || hasRole('admin')")
    def findUser(id: Long): Users = {
      userRepository.findOne(id)
    }

//    @PreAuthorize("hasRole('admin')")
    def create(user: Users): Long = {
      userRepository.save(user)
      user.id
    }
}
