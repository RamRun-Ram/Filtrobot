package ru.sberschool.filtrobot.controller.bot.mock

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import ru.sberschool.filtrobot.controller.bot.FiltrobotController

/**
 * Моковый класс для запуска контекста в тестах
 */
@Profile(value = ["default"])
@Controller
class MockFiltrobotController : FiltrobotController() {

    override fun clearWebhook() {
        // Do nothing
    }

}
