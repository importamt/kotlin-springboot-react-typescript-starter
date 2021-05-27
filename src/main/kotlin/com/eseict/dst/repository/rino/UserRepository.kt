package com.eseict.dst.repository.rino

import com.eseict.dst.entity.rino.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>