package example.com.pokemon.service

import example.com.pokemon.model.Log
import example.com.pokemon.repository.LogRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogService(private val logRepository: LogRepository) {
    fun recordAction(username: String, action: String) {
        val log = Log(
            username = username,
            action = action,
            timestamp = LocalDateTime.now()
        )
        logRepository.save(log)
    }
}