package com.hms.hms.services

import com.hms.hms.dtos.PatientsAddAppDto
import com.hms.hms.entities.Appointment
import com.hms.hms.repositories.AppointmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppointmentService(
    @Autowired
    private val appointmentRepository: AppointmentRepository
) {
    fun saveAppointment(appointmentDetails: PatientsAddAppDto) {
        val appointment = Appointment()

        appointment.appointmentDetails = appointmentDetails.appointmentDetails
        appointment.appointmentDate = appointmentDetails.appointmentDate!!

        appointmentRepository.save(appointment)
    }

    fun getAppointmentByUsername(){

    }

    fun acceptAppointment(patientId: Int) {
        val appointment = appointmentRepository.findByPatientId(patientId)

        appointment.approval = "yes"
    }

    fun changeAppointment(appointmentDate: LocalDateTime, patientId: Int) {
        val appointment = appointmentRepository.findByPatientId(patientId)

        appointment.appointmentDate = appointmentDate
    }
}