package com.hms.hms.services

import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Appointment
import com.hms.hms.entities.Doctor
import com.hms.hms.entities.Patient
import com.hms.hms.entities.User
import com.hms.hms.repositories.AppointmentRepository
import com.hms.hms.repositories.DoctorRepository
import com.hms.hms.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val appointmentRepository: AppointmentRepository,
    @Autowired
    private val doctorRepository: DoctorRepository


) {
    fun save(userRegistrationDetails: RegisterDto): UserDTO {
        val user = User()

        user.firstName = userRegistrationDetails.firstName
        user.lastName = userRegistrationDetails.lastName
        user.username = userRegistrationDetails.username
        user.email = userRegistrationDetails.email
        user.dateOfBirth = userRegistrationDetails.dateOfBirth
        user.password = userRegistrationDetails.password

        userRepository.save(user)

        return user.let {
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

    fun findById(id: Int): User? {
        return userRepository.findById(id)
            .orElse(null)
    }


    fun deleteUserById(id: Int) {
        return userRepository.deleteById(id)
    }

    fun getUsersByRole(roleIds: List<Int>): MutableList<User> {
        return userRepository.findAllById(roleIds)

    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getAllAppointments(): List<Appointment>{
        return appointmentRepository.findAll()
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun getAllDoctors(): MutableList<Doctor> {
        return doctorRepository.findAll()
    }



}