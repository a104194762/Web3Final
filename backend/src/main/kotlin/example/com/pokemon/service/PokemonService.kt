package example.com.pokemon.service

import example.com.pokemon.model.Pokemon
import example.com.pokemon.repository.PokemonRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PokemonService(private val pokemonRepository: PokemonRepository) {
    private val restTemplate = RestTemplate()

    fun fetchAndSavePokemon(name: String): Pokemon? {
        val url = "https://pokeapi.co/api/v2/pokemon/${name.lowercase()}"
        return try {
            val response = restTemplate.getForObject(url, Map::class.java) ?: return null
            val id = (response["id"] as Number).toLong()

            val existingPokemon = pokemonRepository.findById(id).orElse(Pokemon(id = id))

            existingPokemon.nameEn = response["name"] as String
            existingPokemon.imageUrl = (response["sprites"] as Map<*, *>)["front_default"] as String

            val types = response["types"] as List<Map<String, Any>>
            existingPokemon.type = (types[0]["type"] as Map<String, String>)["name"] ?: ""
            existingPokemon.type2 = if (types.size > 1) {
                (types[1]["type"] as Map<String, String>)["name"]
            } else {
                null
            }

            pokemonRepository.save(existingPokemon)
        } catch (e: Exception) {
            null
        }
    }
}