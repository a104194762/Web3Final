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
        return try {
            val auth = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request["username"], request["password"])
            )
            SecurityContextHolder.getContext().authentication = auth
            val session = requestRaw.getSession(true)
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext())
            ResponseEntity.ok("Login successful")
        } catch (e: Exception) {
            println("认证失败详情: ${e.message}") // 这里会打印出具体的 BadCredentialsException
            ResponseEntity.status(401).body("Authentication failed")
        }
    }


    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        SecurityContextHolder.clearContext()

        val session = request.getSession(false)
        session?.invalidate()

        return ResponseEntity.ok("Logged out successfully")
    }

    @GetMapping("/me")
    fun me(): ResponseEntity<Map<String, String>> {
        val auth = SecurityContextHolder.getContext().authentication
        val role = if (auth != null && auth.isAuthenticated && auth.principal != "anonymousUser") {
            auth.authorities.firstOrNull()?.authority ?: ""
        } else {
            ""
        }
        return ResponseEntity.ok(mapOf("role" to role))
    }

}