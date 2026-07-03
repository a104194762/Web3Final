package example.com.pokemon.controller

import example.com.pokemon.model.*
import example.com.pokemon.repository.*
import example.com.pokemon.service.LogService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional

@RestController
@RequestMapping("/api/teams")
class TeamController(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val pokemonRepository: PokemonRepository,
    private val logService: LogService
) {

    @PostMapping("/save")
    @Transactional
    fun saveTeam(@RequestBody request: Map<String, Any>): ResponseEntity<String> {
        val auth = SecurityContextHolder.getContext().authentication
        val principal = auth?.principal as? UserDetails
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in")

        val user = userRepository.findByUsername(principal.username)
            ?: return ResponseEntity.notFound().build()

        val index = request["teamIndex"] as Int
        val name = request["teamName"] as String
        val memberList = (request["members"] as? List<*>)?.mapNotNull { it as? Map<String, Any> } ?: emptyList()

        val team = teamRepository.findByUserIdAndTeamIndex(user.id, index)
            ?: Team(userId = user.id, teamName = name, teamIndex = index)

        team.teamName = name
        team.members.clear()

        val newMembers = memberList.map { m ->
            val pokemonId = (m["id"] as? Number)?.toLong() ?: 0L

            val pokemon = pokemonRepository.findById(pokemonId).orElseGet {
                val newPokemon = Pokemon(
                    id = pokemonId,
                    nameEn = m["nameEn"] as? String ?: "Unknown",
                    imageUrl = m["imageUrl"] as? String ?: ""
                )
                pokemonRepository.save(newPokemon)
            }

            TeamMember(team = team, pokemon = pokemon)
        }

        team.members.addAll(newMembers)
        teamRepository.save(team)

        logService.recordAction(user.username, "Saved team: $name")
        return ResponseEntity.ok("Saved successfully!")
    }

    @GetMapping
    fun getTeams(): ResponseEntity<List<Team>> {
        val auth = SecurityContextHolder.getContext().authentication
        val principal = auth?.principal as? UserDetails
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val user = userRepository.findByUsername(principal.username)
            ?: return ResponseEntity.notFound().build()

        val teams = teamRepository.findByUserId(user.id)

        return ResponseEntity.ok(teams)
    }
}