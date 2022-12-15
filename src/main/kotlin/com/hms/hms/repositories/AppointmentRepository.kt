package com.hms.hms.repositories

import com.hms.hms.entities.Appointment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface AppointmentRepository: JpaRepository<Appointment, Int> {
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM appointment WHERE patient_id = :patientId"
    )
    fun findByPatientId(patientId: Int): Appointment
}
