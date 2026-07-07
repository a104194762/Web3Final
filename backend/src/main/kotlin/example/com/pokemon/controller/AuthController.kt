package example.com.pokemon.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

// authentication controller
@RestController
@RequestMapping("/api/auth")
class AuthController(private val authenticationManager: AuthenticationManager) {

    // handle user login
    @PostMapping("/login")
    fun login(@RequestBody request: Map<String, String>, requestRaw: HttpServletRequest): ResponseEntity<String> {
        return try {
            // verify credentials
            val auth = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request["username"], request["password"])
            )
            // set security context
            SecurityContextHolder.getContext().authentication = auth
            // create session
            val session = requestRaw.getSession(true)
            // store context in session
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext())
            ResponseEntity.ok("Login successful")
        } catch (e: Exception) {
            // print login error
            println("Fail details: ${e.message}")
            ResponseEntity.status(401).body("Authentication failed")
        }
    }

    // handle user logout
    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        // clear security context
        SecurityContextHolder.clearContext()

        // destroy session
        val session = request.getSession(false)
        session?.invalidate()

        return ResponseEntity.ok("Logged out successfully")
    }

    // get current user role
    @GetMapping("/me")
    fun me(): ResponseEntity<Map<String, String>> {
        // fetch current auth data
        val auth = SecurityContextHolder.getContext().authentication
        // extract user role string
        val role = if (auth != null && auth.isAuthenticated && auth.principal != "anonymousUser") {
            auth.authorities.firstOrNull()?.authority ?: ""
        } else {
            ""
        }
        return ResponseEntity.ok(mapOf("role" to role))
    }
}