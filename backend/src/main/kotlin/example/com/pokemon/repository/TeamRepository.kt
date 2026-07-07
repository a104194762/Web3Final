package example.com.pokemon.repository

import example.com.pokemon.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// team database repository
@Repository
interface TeamRepository : JpaRepository<Team, Long> {

    // fetch all teams by user ID
    fun findByUserId(userId: Long): List<Team>

    // find specific team by user and index
    fun findByUserIdAndTeamIndex(userId: Long, teamIndex: Int): Team?
}