package example.com.pokemon.controller

import example.com.pokemon.model.User
import example.com.pokemon.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

// user profile controller
@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
){

    // register new user account
    @PostMapping("/register")
    fun register(@RequestBody user: User): String {
        // encrypt raw password and update object
        user.password = passwordEncoder.encode(user.password).toString()
        // save new user to DB
        userRepository.save(user)
        return "User registered successfully"
    }
}