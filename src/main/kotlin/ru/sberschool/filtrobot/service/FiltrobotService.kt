package ru.sberschool.filtrobot.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Profile(value = ["!default"])
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
            //TODO: ниже воткнуть методы для обработки каждой команды
            when {
                messageText == "/start" -> "Добро пожаловать!"
                messageText.startsWith("/subscribe") -> "Подписка на канал"
                messageText.startsWith("/addPref") -> "Добавляем предпочтение"
                messageText.startsWith("/rmPref") -> "Убираем предпочтение"
                messageText.startsWith("/unsubscribe") -> "Отменяем подписку"
                else -> "Я не знаю такой команды :с"
            }
        } else {
            "Я понимаю только текст :с"
        }

        sendResponse(chatId, responseText)
    }

    private fun sendResponse(chatId: Long, responseText: String) {
        val responseMessage = SendMessage(chatId.toString(), responseText)
        responseMessage.enableMarkdown(true)
        execute(responseMessage)
    }

}
