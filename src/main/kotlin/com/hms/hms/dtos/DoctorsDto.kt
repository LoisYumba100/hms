package com.hms.hms.dtos

import java.time.LocalDate

class DoctorsDto {
    var appointmentDetails : String = ""
    var appointmentTime: LocalDate = LocalDate.now()
    var patientId: Int = 0
    var doctorId = 0

}