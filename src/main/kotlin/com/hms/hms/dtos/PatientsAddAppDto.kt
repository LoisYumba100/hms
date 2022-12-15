package com.hms.hms.dtos

import com.hms.hms.entities.Patient
import com.hms.hms.entities.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class PatientsAddAppDto(
    var appointmentDetails : String = "",
    var appointmentDate: LocalDateTime = LocalDateTime.now(),
    var patient: Patient? = null
)