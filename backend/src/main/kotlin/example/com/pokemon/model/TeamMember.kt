package example.com.pokemon.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

// junction entity for team members
@Entity
@Table(name = "team_members")
class TeamMember(
    // primary key auto-increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    // parent team relation (hidden from JSON)
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    var team: Team? = null,

    // mapped pokemon relation
    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon? = null
)