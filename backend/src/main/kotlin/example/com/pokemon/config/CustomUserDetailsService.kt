package example.com.pokemon.config

import example.com.pokemon.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")
        println("Password read from the database: ${user.password}")


        val role = if (user.role.startsWith("ROLE_")) user.role.removePrefix("ROLE_") else user.role
        return User.withUsername(user.username)
            .password(user.password)
            .roles(role)
            .build()
    }
}