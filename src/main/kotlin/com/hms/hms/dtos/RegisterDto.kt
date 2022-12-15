package com.hms.hms.dtos

import java.time.LocalDate

class RegisterDto {
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var dateOfBirth: LocalDate = LocalDate.now()
    var password: String = ""
    var username: String = ""
}