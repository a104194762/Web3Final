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

// team management controller
@RestController
@RequestMapping("/api/teams")
class TeamController(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val pokemonRepository: PokemonRepository,
    private val logService: LogService
) {

    // save or update team composition
    @PostMapping("/save")
    @Transactional
    fun saveTeam(@RequestBody request: Map<String, Any>): ResponseEntity<String> {
        // fetch current auth data
        val auth = SecurityContextHolder.getContext().authentication
        val principal = auth?.principal as? UserDetails
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in")

        // check user existence in DB
        val user = userRepository.findByUsername(principal.username)
            ?: return ResponseEntity.notFound().build()

        // parse request metadata
        val index = request["teamIndex"] as Int
        val name = request["teamName"] as String
        val memberList = (request["members"] as? List<*>)?.mapNotNull { it as? Map<String, Any> } ?: emptyList()

        // fetch existing team or create empty
        val team = teamRepository.findByUserIdAndTeamIndex(user.id, index)
            ?: Team(userId = user.id, teamName = name, teamIndex = index)

        // update team name and clear old members
        team.teamName = name
        team.members.clear()

        // map request list to new member entities
        val newMembers = memberList.map { m ->
            val pokemonId = (m["id"] as? Number)?.toLong() ?: 0L

            // get pokemon or create fallback cache record
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

        // assign members and save team
        team.members.addAll(newMembers)
        teamRepository.save(team)

        // record user action to log history
        logService.recordAction(user.username, "Saved team: $name")
        return ResponseEntity.ok("Saved successfully!")
    }

    // fetch all teams owned by user
    @GetMapping
    fun getTeams(): ResponseEntity<List<Team>> {
        // fetch current auth data
        val auth = SecurityContextHolder.getContext().authentication
        val principal = auth?.principal as? UserDetails
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        // check user existence in DB
        val user = userRepository.findByUsername(principal.username)
            ?: return ResponseEntity.notFound().build()

        // return user teams list
        val teams = teamRepository.findByUserId(user.id)
        return ResponseEntity.ok(teams)
    }
}