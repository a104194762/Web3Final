package example.com.pokemon.service

import example.com.pokemon.model.Pokemon
import example.com.pokemon.repository.PokemonRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

// pokemon synchronization service
@Service
class PokemonService(private val pokemonRepository: PokemonRepository) {
    // HTTP client instance
    private val restTemplate = RestTemplate()

    // fetch data from PokeAPI and sync with DB
    fun fetchAndSavePokemon(name: String): Pokemon? {
        val url = "https://pokeapi.co/api/v2/pokemon/${name.lowercase()}"
        return try {
            // send external GET request
            val response = restTemplate.getForObject(url, Map::class.java) ?: return null
            // extract pokemon runtime ID
            val id = (response["id"] as Number).toLong()

            // load existing record or create stub
            val existingPokemon = pokemonRepository.findById(id).orElse(Pokemon(id = id))

            // extract basic structural payload properties
            existingPokemon.nameEn = response["name"] as String
            existingPokemon.imageUrl = (response["sprites"] as Map<*, *>)["front_default"] as String

            // parse elemental type array
            val types = response["types"] as List<Map<String, Any>>
            // map primary type mapping
            existingPokemon.type = (types[0]["type"] as Map<String, String>)["name"] ?: ""
            // map secondary type mapping conditionally
            existingPokemon.type2 = if (types.size > 1) {
                (types[1]["type"] as Map<String, String>)["name"]
            } else {
                null
            }

            // commit and update DB cache record
            pokemonRepository.save(existingPokemon)
        } catch (e: Exception) {
            // fallback error boundary return
            null
        }
    }
}