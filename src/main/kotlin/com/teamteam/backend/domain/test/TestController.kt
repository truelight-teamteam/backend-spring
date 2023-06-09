package com.teamteam.backend.domain.test

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @Operation(tags = ["ping"])
    @GetMapping("/ping")
    fun ping() = "ping"
}