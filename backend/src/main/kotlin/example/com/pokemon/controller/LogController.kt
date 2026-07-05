package example.com.pokemon.controller

import example.com.pokemon.model.Log
import example.com.pokemon.repository.LogRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/logs")
class LogController(private val logRepository: LogRepository) {
    @GetMapping
    fun getAllLogs(): List<Log> {
        return logRepository.findAll()
    }
}