package com.hms.hms.controllers

import com.hms.hms.dtos.PatientsAddAppDto
import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Appointment
import com.hms.hms.entities.Doctor
import com.hms.hms.entities.Patient
import com.hms.hms.services.AppointmentService
import com.hms.hms.services.DoctorService
import com.hms.hms.services.UserService
import com.hms.hms.services.PatientService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/doctor")
class DoctorController(
    @Autowired
    private val userService: UserService,
    @Autowired
    private val appointmentService: AppointmentService,
    @Autowired
    private val doctorService: DoctorService,
    @Autowired
    private val patientService: PatientService

) {
    @PostMapping("/approve")
    fun approveAppointmentDate(
        @RequestBody
        appointmentDetails: PatientsAddAppDto,
        servletRequest: HttpServletRequest
    ){
        val doctorId = servletRequest.session.id.toInt()
        val doctor = doctorService.findById(doctorId)

        if (doctor !== null) {
            appointmentDetails.patient?.let { appointmentService.acceptAppointment(it.id) }
        }
    }

    @PostMapping("/change-date")
    fun changeAppointmentDate(
        @RequestBody
        appointmentDetails: PatientsAddAppDto,
        servletRequest: HttpServletRequest
    ){
        val doctorId = servletRequest.session.id.toInt()
        val doctor = doctorService.findById(doctorId)

        if (doctor !== null) {
            appointmentDetails.patient?.let { appointmentService.changeAppointment(appointmentDetails.appointmentDate, it.id) }
        }
    }

    @GetMapping("/get-all-appointments")
    fun showAllAppointments(): ResponseEntity<List<Appointment>>{
        return ResponseEntity.ok(doctorService.getAllAppointments())

    }
    @GetMapping("/get-all-patients")
    fun getPatients(): MutableList<Patient> {
        return doctorService.getAllPatients()
    }

    @PostMapping("/add-patient")
    fun addPatient(@RequestBody patientsRegistrationDetails:RegisterDto): ResponseEntity<Unit> {

        return ResponseEntity.ok(patientService.savePatient(patientsRegistrationDetails))

    }
}