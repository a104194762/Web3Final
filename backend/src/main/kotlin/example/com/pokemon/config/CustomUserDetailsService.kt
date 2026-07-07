package example.com.pokemon.config

import example.com.pokemon.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

// custom user service for authentication
@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    // load user by username
    override fun loadUserByUsername(username: String): UserDetails {
        // fetch user from DB
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        // debug log for password check
        println("Password read from the database: ${user.password}")

        // format user role string
        val role = if (user.role.startsWith("ROLE_")) user.role.removePrefix("ROLE_") else user.role

        // return security user object
        return User.withUsername(user.username)
            .password(user.password)
            .roles(role)
            .build()
    }
}