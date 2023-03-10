package com.hms.hms.controllers

import com.hms.hms.config.JwtProperties
import com.hms.hms.dtos.LoginDto
import com.hms.hms.dtos.RegisterDto
import com.hms.hms.dtos.UserDTO
import com.hms.hms.entities.Message
import com.hms.hms.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/auth/")
class AuthController(
    @Autowired
    private val userService: UserService,
    @Autowired
    private val jwtProperties: JwtProperties
) {

    @PostMapping("register") // localhost:8080/api/auth/register
    fun register(
        @RequestBody
        userRegistrationDetails: RegisterDto
    ): ResponseEntity<UserDTO> {
        // simply retrieve information from the request body and save it to the database as a new user
        return ResponseEntity.ok(userService.save(userRegistrationDetails))
    }

    @PostMapping("login")
    fun login(
        @RequestBody
        userLoginDetails: LoginDto,
        response: HttpServletResponse
    ): ResponseEntity<Any> {
        // check if user exists using email else return message "User not found"
        val user = userService.findByEmail(userLoginDetails.email)
            ?: return ResponseEntity.badRequest().body(Message("User not found"))

        // check if password is correct else return message "Incorrect password"
        if (!user.comparePassword(userLoginDetails.password)) {
            return ResponseEntity.badRequest().body(Message("Incorrect password"))
        }

        // populate jsonwebtoken with values including user id
        val issuer = user.id.toString()

        // time jsonwebtoken is valid for
        val expiryDate = Date(System.currentTimeMillis() + 60 * 24 * 1000) // 24 hours

        // create jsonwebtoken
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .compact()

        // create cookie with jsonwebtoken
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        // add cookie to response
        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Login successful"))
    }

    @PostMapping("logout")
    fun logout(
        response: HttpServletResponse
    ): ResponseEntity<Any> {
        // create cookie with empty value (this signifies that the user is logged out)
        val cookie = Cookie("jwt", "")
        cookie.isHttpOnly = true

        // add cookie to response
        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logout successful"))
    }
}