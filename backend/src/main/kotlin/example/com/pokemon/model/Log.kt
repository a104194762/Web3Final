package example.com.pokemon.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "logs")
class Log(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var username: String = "",
    var action: String = "",
    var timestamp: java.time.LocalDateTime = java.time.LocalDateTime.now()
)