package ru.sberschool.filtrobot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class LandingPageController {

    @GetMapping
    fun getLandingPage(): String {
        return "landing"
    }

}
