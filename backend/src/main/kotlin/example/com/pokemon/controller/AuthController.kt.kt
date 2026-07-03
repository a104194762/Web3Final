package example.com.pokemon.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authenticationManager: AuthenticationManager) {

    @PostMapping("/login")
    fun login(@RequestBody request: Map<String, String>, requestRaw: HttpServletRequest): ResponseEntity<String> {
        val username = request["username"] ?: ""
        val password = request["password"] ?: ""

        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        SecurityContextHolder.getContext().authentication = auth

        val session = requestRaw.getSession(true)
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext())

        return ResponseEntity.ok("Login successful")
    }
    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        SecurityContextHolder.clearContext()

        val session = request.getSession(false)
        session?.invalidate()

        return ResponseEntity.ok("Logged out successfully")
    }
}