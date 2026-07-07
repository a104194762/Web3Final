package example.com.pokemon.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

// security config
@Configuration
@EnableWebSecurity
class SecurityConfig {

    // configure security rules
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // disable CSRF
            .csrf { it.disable() }
            // enable CORS config
            .cors { it.configurationSource(corsConfigurationSource()) }
            // session policy
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) }
            // URL authorization rules
            .authorizeHttpRequests { auth ->
                // permit preflight requests
                auth.requestMatchers(org.springframework.web.cors.CorsUtils::isPreFlightRequest).permitAll()
                // permit auth routes
                auth.requestMatchers("/api/users/register", "/api/auth/login", "/api/auth/logout").permitAll()
                // restrict logs to admin
                auth.requestMatchers("/api/logs/**").hasRole("ADMIN")
                // authenticate other routes
                auth.anyRequest().authenticated()
            }
        // print initialization status
        println("Security FilterChain")
        return http.build()
    }

    // CORS configuration definitions
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        // allowed origin
        configuration.allowedOrigins = listOf("http://localhost:5173")
        // allowed HTTP methods
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        // allowed headers
        configuration.allowedHeaders = listOf("*")
        // enable credentials cookies
        configuration.allowCredentials = true

        // register global mapping
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    // password encoder bean
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // authentication manager bean
    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager
}