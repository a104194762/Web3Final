package example.com.pokemon.service

import example.com.pokemon.model.Log
import example.com.pokemon.repository.LogRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

// log transaction service
@Service
class LogService(private val logRepository: LogRepository) {

    // create and save system logs
    fun recordAction(username: String, action: String) {
        // initialize log data entity
        val log = Log(
            username = username,
            action = action,
            timestamp = LocalDateTime.now()
        )
        // persist log record to DB
        logRepository.save(log)
    }
}