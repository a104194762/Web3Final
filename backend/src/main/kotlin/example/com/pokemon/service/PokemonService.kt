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

            // 1. 尝试从数据库获取现有记录，如果没有则创建一个新的
            val existingPokemon = pokemonRepository.findById(id).orElse(Pokemon(id = id))

            // 2. 更新基础信息
            existingPokemon.nameEn = response["name"] as String
            existingPokemon.imageUrl = (response["sprites"] as Map<*, *>)["front_default"] as String

            // 3. 智能解析双属性
            val types = response["types"] as List<Map<String, Any>>
            existingPokemon.type = (types[0]["type"] as Map<String, String>)["name"] ?: ""
            existingPokemon.type2 = if (types.size > 1) {
                (types[1]["type"] as Map<String, String>)["name"]
            } else {
                null // 没有第二属性则设为 null
            }

            // 4. 保存（如果是已存在 ID，JPA 会自动执行更新 SQL）
            pokemonRepository.save(existingPokemon)
        } catch (e: Exception) {
            null
        }
    }
}