package ru.sberschool.filtrobot.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User

private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

@ExtendWith(MockitoExtension::class)
internal class FiltrobotServiceTest {

    private val bot: FiltrobotService = mock(FiltrobotService::class.java)

    @Captor
    lateinit var responseCaptor: ArgumentCaptor<SendMessage>

    @BeforeEach
    fun setUp() {
        Mockito.doCallRealMethod().`when`(bot).onUpdateReceived(any(Update::class.java))
    }

    @Test
    fun `onUpdateReceived with start command`() {
        val cmd = "/start"
        val update = buildUpdate(cmd)

        bot.onUpdateReceived(update)

        assertResponseContains("Привет", "Мои команды")
    }

    @Test
    fun `onUpdateReceived with subscribe command`() {
        val cmd = "/subscribe @TestChannel"
        val update = buildUpdate(cmd)

        bot.onUpdateReceived(update)

        assertResponseEqualsTo("Подписка на канал")
    }

    @Test
    fun `onUpdateReceived with unknown command`() {
        val cmd = "/unknown"
        val update = buildUpdate(cmd)

        bot.onUpdateReceived(update)

        assertResponseEqualsTo("Я не знаю такой команды :с")
    }

    private fun buildUpdate(messageText: String, chatId: Long = 0): Update {
        val chat = Chat()
        chat.id = chatId

        val message = Message()
        message.text = messageText
        message.chat = chat
        message.from = User()

        val update = Update()
        update.message = message

        return update
    }

    private fun assertResponseEqualsTo(expected: String) {
        Mockito.verify(bot).execute(responseCaptor.capture())
        val response = responseCaptor.value
        assertEquals(expected, response.text)
    }

    private fun assertResponseContains(vararg expected: String) {
        Mockito.verify(bot).execute(responseCaptor.capture())
        val response = responseCaptor.value
        expected.forEach {
            assertTrue(response.text.contains(it))
        }
    }
}
