package com.hms.hms.repositories

import com.hms.hms.entities.Doctor
import com.hms.hms.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DoctorRepository: JpaRepository<Doctor, Int> {
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
}