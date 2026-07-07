package example.com.pokemon.model

import jakarta.persistence.*
import java.time.LocalDateTime

// log database entity
@Entity
@Table(name = "logs")
class Log(
    // primary key auto-increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    // actor username
    var username: String = "",
    // description of activity
    var action: String = "",
    // creation date and time
    var timestamp: LocalDateTime = LocalDateTime.now()
)