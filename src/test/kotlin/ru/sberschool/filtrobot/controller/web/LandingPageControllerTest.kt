package ru.sberschool.filtrobot.controller.web

import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
internal class LandingPageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        //TODO: добавить заполнение тестовыми данными, когда появится логика у лэндинга
    }

    @Test
    fun getLandingPage() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
