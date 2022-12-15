package com.hms.hms.dtos

import java.time.LocalDate

data class UserDTO (
    var id: Int? = null,
    var firstName: String = "",
    var lastName: String = "",
    var username: String = "",
    var email: String = "",
    var dateOfBirth: LocalDate? = LocalDate.now(),
    var password: String = "",
)