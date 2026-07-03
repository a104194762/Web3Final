package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "team_members")
class TeamMember(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    var team: Team? = null,

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon? = null
)