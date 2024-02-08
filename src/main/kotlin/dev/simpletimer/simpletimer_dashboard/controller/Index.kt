package dev.simpletimer.simpletimer_dashboard.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Index {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}