package com.hms.hms.controllers

import com.hms.hms.dtos.PatientsAddAppDto
import com.hms.hms.dtos.RegisterDto
import com.hms.hms.entities.Appointment
import com.hms.hms.entities.User
import com.hms.hms.services.UserService
import com.hms.hms.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import com.hms.hms.services.AppointmentService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/patient")
class PatientsController  (
    @Autowired
    private val userService: UserService,

    @Autowired
    private val patientService: PatientService,

    @Autowired
    private val appointmentService: AppointmentService
){
    @PostMapping("/register-patient")
    fun registerPatient(
        @RequestBody
        patientRegistrationDetails: RegisterDto
    ): ResponseEntity<Unit> {
        val user = User()

        return ResponseEntity.ok(patientService.savePatient(patientRegistrationDetails))
    }

    @PostMapping("/create-appointment")
    fun createAppointment(
        @RequestBody
        appointmentDetails: PatientsAddAppDto
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointmentDetails))
    }

        @GetMapping("/get-appointment-for-patient")
        fun getAppointmentsById(@PathVariable("appointmentId") appointmentId : Int): ResponseEntity<Appointment> {
            return ResponseEntity.ok(patientService.getAppointmentsById(appointmentId))
    }

    @GetMapping("/get-patients-appointment")
    fun getPatientsAppointments(): ResponseEntity<List<Appointment>> {
        return ResponseEntity.ok(patientService.getAllAppointments())
    }
}