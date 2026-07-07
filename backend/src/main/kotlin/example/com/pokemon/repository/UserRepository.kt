package example.com.pokemon.repository

import example.com.pokemon.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// user database repository
@Repository
interface UserRepository : JpaRepository<User, Long> {

    // fetch unique user by username
    fun findByUsername(username: String): User?
}