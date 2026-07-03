package example.com.pokemon.repository

import example.com.pokemon.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<Pokemon, Long> {
    fun findByType(type: String): List<Pokemon>

    fun findByTypeOrType2(type: String, type2: String?): List<Pokemon>

    @Query("SELECT p FROM Pokemon p WHERE LOWER(p.nameEn) LIKE LOWER(CONCAT('%', :query, '%'))")
    fun searchByMultiLanguage(@Param("query") query: String): List<Pokemon>

}