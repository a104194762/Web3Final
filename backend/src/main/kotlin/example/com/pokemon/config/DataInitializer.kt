package example.com.pokemon.config

import example.com.pokemon.model.User
import example.com.pokemon.model.Pokemon
import example.com.pokemon.repository.UserRepository
import example.com.pokemon.repository.PokemonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class DataInitializer(
    private val userRepository: UserRepository,
    private val pokemonRepository: PokemonRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Bean
    fun initDatabase() = CommandLineRunner {
        if (userRepository.findByUsername("admin") == null) {
            val admin = User(
                username = "admin",
                password = passwordEncoder.encode("123456") ?: "default_fallback_password",
                role = "admin"
            )
            userRepository.save(admin)
        }
    }
}