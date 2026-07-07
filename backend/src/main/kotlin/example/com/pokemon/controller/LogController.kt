package example.com.pokemon.controller

import example.com.pokemon.model.Log
import example.com.pokemon.repository.LogRepository
import org.springframework.web.bind.annotation.*

// log tracking controller
@RestController
@RequestMapping("/api/logs")
class LogController(private val logRepository: LogRepository) {

    // fetch all system logs
    @GetMapping
    fun getAllLogs(): List<Log> {
        // return full log list from DB
        return logRepository.findAll()
    }
}