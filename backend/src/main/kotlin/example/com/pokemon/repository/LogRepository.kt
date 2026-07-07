package example.com.pokemon.repository

import example.com.pokemon.model.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// log database repository
@Repository
interface LogRepository : JpaRepository<Log, Long>