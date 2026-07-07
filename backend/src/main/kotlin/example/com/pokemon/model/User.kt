package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

// user database entity
@Entity
@Table(name = "users")
class User(
    // primary key auto-increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    // account identity name
    var username: String = "",

    // hashed password string
    var password: String = "",

    // authorization role status
    var role: String = "USER"
)