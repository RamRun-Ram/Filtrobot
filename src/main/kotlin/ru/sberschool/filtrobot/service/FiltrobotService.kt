package ru.sberschool.filtrobot.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User

//@Profile(value = ["!default"])
@Service
class FiltrobotService : TelegramLongPollingBot() {

    @Value(value = "\${telegram.bot.name}")
    lateinit var name: String

    @Value(value = "\${telegram.bot.token}")
    lateinit var token: String

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = name

    override fun onUpdateReceived(update: Update) {
        if (!update.hasMessage()) {
            return
        }

        val message = update.message
        val chatId = message.chatId
        val user = message.from

        val responseText = if (message.hasText()) {
            val messageText = message.text
            when {
                messageText == "/start" -> getWelcomeInstruction()
                messageText.startsWith("/subscribe") -> subscribeUser(messageText, user)
                messageText.startsWith("/addPref") -> addPreference(messageText, user)
                messageText.startsWith("/rmPref") -> removePreference(messageText, user)
                messageText.startsWith("/unsubscribe") -> unsubscribeUser(messageText, user)
                else -> "Я не знаю такой команды :с"
            }
        } else {
            "Я понимаю только текст :с"
        }

        sendResponse(chatId, responseText)
    }

    /**
     * Вызывается при старте бота, возвращает список доступных команд с описанием, что и как работает
     * TODO: сделать начинку
     */
    private fun getWelcomeInstruction(): String {
        return "Добро пожаловать!"
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun subscribeUser(messageText: String, user: User): String {
        return "Подписка на канал"
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun addPreference(messageText: String, user: User): String {
        return "Добавляем предпочтение"
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun removePreference(messageText: String, user: User): String {
        return "Убираем предпочтение"
    }

    /**
     * Отменяет подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun unsubscribeUser(messageText: String, user: User): String {
        return "Отмена подписки на канал"
    }

    /**
     * Формирует ответное сообщение бота и отправляет его в указанный чат
     */
    private fun sendResponse(chatId: Long, responseText: String) {
        val responseMessage = SendMessage(chatId.toString(), responseText)
        responseMessage.enableMarkdown(true)
        execute(responseMessage)
    }

}
