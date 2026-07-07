package example.com.pokemon.controller

import example.com.pokemon.model.Pokemon
import example.com.pokemon.repository.PokemonRepository
import example.com.pokemon.service.LogService
import example.com.pokemon.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

// pokemon management controller
@RestController
@RequestMapping("/api/pokemons")
class PokemonController(
    private val pokemonRepository: PokemonRepository,
    private val pokemonService: PokemonService,
    private val logService: LogService
) {

    // get pokemon list or filter by type
    @GetMapping
    fun getPokemons(@RequestParam(required = false) type: String?): List<Pokemon> {
        return if (!type.isNullOrEmpty()) {
            // filter by primary or secondary type
            pokemonRepository.findByTypeOrType2(type, type)
        } else {
            // return all pokemon
            pokemonRepository.findAll()
        }
    }

    // search pokemon with fallback sync
    @GetMapping("/search")
    fun searchPokemons(@RequestParam query: String): ResponseEntity<List<Pokemon>> {
        // fetch user authentication info
        val auth = SecurityContextHolder.getContext().authentication
        if (auth?.principal is UserDetails) {
            // log search action to DB
            logService.recordAction((auth.principal as UserDetails).username, "Searched for: $query")
        }

        // check local DB cache
        val localResults = pokemonRepository.searchByMultiLanguage(query)

        // if found locally
        if (localResults.isNotEmpty()) {
            val firstResult = localResults[0]
            // check if detail data is missing
            if (firstResult.type.isNullOrEmpty()) {
                // fetch full details from external API
                val updated = pokemonService.fetchAndSavePokemon(query)
                return ResponseEntity.ok(listOf(updated ?: firstResult))
            }
            return ResponseEntity.ok(localResults)
        }

        // if not found locally, fetch from external API
        val newPokemon = pokemonService.fetchAndSavePokemon(query)
        return if (newPokemon != null) {
            ResponseEntity.ok(listOf(newPokemon))
        } else {
            ResponseEntity.ok(emptyList<Pokemon>())
        }
    }
}