package example.com.pokemon.controller

import example.com.pokemon.model.User
import example.com.pokemon.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
){

    @PostMapping("/register")
    fun register(@RequestBody user: User): String {
        val raw = user.password ?: ""
        val encoded = passwordEncoder.encode(raw) ?: ""
        user.password = passwordEncoder.encode(user.password).toString()
        userRepository.save(user)
        return "User registered successfully"
    }

    @PostMapping("/login")
    fun login(@RequestBody request: Map<String, String>): ResponseEntity<String> {
        val username = request["username"] ?: ""
        val password = request["password"] ?: ""

        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )
        SecurityContextHolder.getContext().authentication = auth
        return ResponseEntity.ok("Login successful")
    }
}