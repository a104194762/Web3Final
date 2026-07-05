package example.com.pokemon.controller

import example.com.pokemon.model.Pokemon
import example.com.pokemon.repository.PokemonRepository
import example.com.pokemon.service.LogService
import example.com.pokemon.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

@RestController
@RequestMapping("/api/pokemons")
class PokemonController(
    private val pokemonRepository: PokemonRepository,
    private val pokemonService: PokemonService,
    private val logService: LogService
) {

    @GetMapping
    fun getPokemons(@RequestParam(required = false) type: String?): List<Pokemon> {
        return if (!type.isNullOrEmpty()) {
            pokemonRepository.findByTypeOrType2(type, type)
        } else {
            pokemonRepository.findAll()
        }
    }

    @GetMapping("/search")
    fun searchPokemons(@RequestParam query: String): ResponseEntity<List<Pokemon>> {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth?.principal is UserDetails) {
            logService.recordAction((auth.principal as UserDetails).username, "Searched for: $query")
        }

        val localResults = pokemonRepository.searchByMultiLanguage(query)

        if (localResults.isNotEmpty()) {
            val firstResult = localResults[0]
            if (firstResult.type.isNullOrEmpty()) {
                val updated = pokemonService.fetchAndSavePokemon(query)
                return ResponseEntity.ok(listOf(updated ?: firstResult))
            }
            return ResponseEntity.ok(localResults)
        }

        val newPokemon = pokemonService.fetchAndSavePokemon(query)
        return if (newPokemon != null) {
            ResponseEntity.ok(listOf(newPokemon))
        } else {
            ResponseEntity.ok(emptyList<Pokemon>())
        }
    }
}