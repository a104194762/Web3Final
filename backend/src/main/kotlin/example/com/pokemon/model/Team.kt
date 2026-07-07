package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

// pokemon team database entity
@Entity
@Table(name = "teams")
class Team(
    // primary key auto-increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    // owner user identifier
    @JsonIgnore
    var userId: Long? = null,

    // custom layout position index
    var teamIndex: Int = 0,
    // display name of team
    var teamName: String = "",

    // child elements mapping
    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var members: MutableList<TeamMember> = mutableListOf()
) {
    // no-argument fallback constructor
    constructor() : this(0, null, 0, "", mutableListOf())
}