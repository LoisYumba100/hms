package com.hms.hms.controllers

import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Doctor
import com.hms.hms.entities.Message
import com.hms.hms.entities.Patient
import com.hms.hms.entities.User
import com.hms.hms.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminController(
    @Autowired
    private val userService: UserService
) {
    @PostMapping("/add-admin")
    fun addAdmin(
        @RequestBody
        adminRegistrationDetails: RegisterDto
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.save(adminRegistrationDetails))
    }



    @PostMapping("/add-doctor")
    fun addDoctor(
        @RequestBody
        doctorRegistrationDetails: RegisterDto
    ): ResponseEntity<UserDTO> {

        return ResponseEntity.ok(userService.save(doctorRegistrationDetails))
    }


    @DeleteMapping("/remove-doctor")
    fun removeDoctor(
        @RequestBody
        doctorId: Int
    ): ResponseEntity<Any> {
        val doctor = userService.findById(doctorId)
            ?: return ResponseEntity.badRequest().body(Message("doctor not found"))

        return ResponseEntity.ok(userService.deleteUserById(doctor.id))
    }

    @GetMapping("/get-all-doctors")
    fun getPatients(): MutableList<Doctor> {
        return userService.getAllDoctors()
    }
    }

