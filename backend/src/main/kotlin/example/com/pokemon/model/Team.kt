package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name = "teams")
class Team(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @JsonIgnore
    var userId: Long? = null,

    var teamIndex: Int = 0,
    var teamName: String = "",

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var members: MutableList<TeamMember> = mutableListOf()
) {
    constructor() : this(0, null, 0, "", mutableListOf())
}