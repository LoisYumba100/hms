package com.hms.hms.services

import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Appointment
import com.hms.hms.entities.Patient
import com.hms.hms.repositories.AppointmentRepository
import com.hms.hms.repositories.DoctorRepository
import com.hms.hms.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PatientService (
    @Autowired
    private val patientRepository: PatientRepository,
    @Autowired
    private val appointmentRepository: AppointmentRepository


        )
{
    fun savePatient(PatientRegistrationDetails: RegisterDto){
        val patient = Patient()

        patient.firstName = PatientRegistrationDetails.firstName
        patient.lastName = PatientRegistrationDetails.lastName
        patient.username = PatientRegistrationDetails.username
        patient.email = PatientRegistrationDetails.email
        patient.dateOfBirth = PatientRegistrationDetails.dateOfBirth
        patient.password = PatientRegistrationDetails.password

        patientRepository.save(patient)

        return patient.let {
            UserDTO(
                it.id,
                it.firstName,
                it.lastName,
                it.username,
                it.email,
                it.dateOfBirth,
                it.password

            )

        }


    }
     fun getAppointmentsById(appointmentId: Int): Appointment? {
        return appointmentRepository.findByPatientId(appointmentId)
  }

    fun getAllAppointments(): List<Appointment>{
        return appointmentRepository.findAll()
    }

}