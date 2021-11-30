package ru.sberschool.filtrobot.service.mock

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.sberschool.filtrobot.service.FiltrobotService

@Profile(value = ["default"])
@Service
class MockFiltrobotService : FiltrobotService() {

    override fun clearWebhook() {
        //Do nothing
    }

}
