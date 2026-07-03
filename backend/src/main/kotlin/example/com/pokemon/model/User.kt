package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name = "users")

class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var username: String = "",

    @JsonIgnore
    var password: String = "",

    var role: String = "USER",

    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL], orphanRemoval = true)
    var teams: MutableList<Team> = mutableListOf()
)