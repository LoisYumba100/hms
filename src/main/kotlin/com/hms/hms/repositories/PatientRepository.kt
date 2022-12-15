package com.hms.hms.repositories

import com.hms.hms.entities.Patient
import com.hms.hms.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PatientRepository: JpaRepository<Patient, Int>{
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
}