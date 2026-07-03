package example.com.pokemon.repository

import example.com.pokemon.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Long> {
    fun findByUserId(userId: Long): List<Team>
    fun findByUserIdAndTeamIndex(userId: Long, teamIndex: Int): Team?
}