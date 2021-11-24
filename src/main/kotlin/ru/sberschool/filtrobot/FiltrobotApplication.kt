package ru.sberschool.filtrobot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FiltrobotApplication

fun main(args: Array<String>) {
    runApplication<FiltrobotApplication>(*args)
}
