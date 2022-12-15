package com.hms.hms.services

import com.hms.hms.dtos.DoctorsDto
import com.hms.hms.dtos.PatientsAddAppDto
import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Appointment
import com.hms.hms.entities.Doctor
import com.hms.hms.entities.Patient
import com.hms.hms.entities.User
import com.hms.hms.repositories.AppointmentRepository
import com.hms.hms.repositories.DoctorRepository
import com.hms.hms.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DoctorService(
    @Autowired
    private val doctorRepository: DoctorRepository,
    @Autowired
    private val appointmentRepository: AppointmentRepository,
    @Autowired
    private val patientRepository: PatientRepository

) {
    fun saveDoctor(DoctorRegistrationDetails: RegisterDto): UserDTO{
        val doctor = Doctor()
        doctor.firstName = DoctorRegistrationDetails.firstName
        doctor.lastName = DoctorRegistrationDetails.lastName
        doctor.username = DoctorRegistrationDetails.username
        doctor.email = DoctorRegistrationDetails.email
        doctor.dateOfBirth = DoctorRegistrationDetails.dateOfBirth
        doctor.password = DoctorRegistrationDetails.password

        doctorRepository.save(doctor)

        return doctor.let {
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


    fun getUsersByRole(roleIds: List<Int>): MutableList<Patient> {
        return patientRepository.findAllById(roleIds)

    }

    fun getAppointmentsById(appointmentIds: List<Int>): MutableList<Appointment>{
        return appointmentRepository.findAllById(appointmentIds)
    }

    fun findByEmail(email: String): User? {
        return patientRepository.findByEmail(email)
    }

    fun getAllPatients(): MutableList<Patient> {
        return patientRepository.findAll()
    }

    fun getAllAppointments(): List<Appointment>{
        return appointmentRepository.findAll()
    }

    fun findByUsername(username: String): User? {
        return patientRepository.findByUsername(username)
    }

    fun findById(id: Int): Doctor? {
        return doctorRepository.findById(id)
            .orElse(null)
    }
}