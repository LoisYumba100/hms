package com.hms.hms.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate


@Entity
@Table(name = "doctor", schema = "public")
class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    var id: Int = 0

    @Column(name = "first_name")
    var firstName : String = ""

    @Column(name = "last_name")
    var lastName: String = ""

    @Column(name = "user_name" , unique = true)
    var username: String = ""

    @Column(name = "email")
    var email: String = ""

    @Column(name = "password", unique = true)
    var password: String = ""
    @JsonIgnore
    get
        set(value){
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }
    fun comparePassword(password: String): Boolean {
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.matches(password, this.password)
    }



    @Column(name = "date_of_birth")
    var dateOfBirth: LocalDate = LocalDate.now()



}