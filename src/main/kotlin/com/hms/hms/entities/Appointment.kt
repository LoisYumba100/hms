package com.hms.hms.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
@Table(name = "appointment", schema ="public")
class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "appointment_details")
    var appointmentDetails: String = ""

    @Column(name = "appointment_date")
    var appointmentDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "approval")
    var approval: String = "no"

    @ManyToOne
    var patient: Patient = Patient()
}