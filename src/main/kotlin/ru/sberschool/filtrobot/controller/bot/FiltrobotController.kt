package ru.sberschool.filtrobot.controller.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import ru.sberschool.filtrobot.vo.BotResponse

@Profile(value = ["local", "heroku"])
@Controller
class FiltrobotController : TelegramLongPollingBot() {

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
                messageText == "/help" -> getHelpInstruction()
                messageText.startsWith("/subscribe") -> subscribeUser(messageText, user)
                messageText.startsWith("/addPreference") -> addPreference(messageText, user)
                messageText.startsWith("/removePreference") -> removePreference(messageText, user)
                messageText.startsWith("/unsubscribe") -> unsubscribeUser(messageText, user)
                else -> BotResponse("Я не знаю такой команды :с")
            }
        } else {
            BotResponse("Я понимаю только текст :с")
        }

        sendResponse(chatId, responseText)
    }

    /**
     * Вызывается при старте бота, возвращает список доступных команд с описанием, что и как работает
     * TODO: как-то запариться с форматированием бы, а то не очень красиво
     */
    private fun getWelcomeInstruction(): BotResponse {
        return BotResponse(
            """Привет, я @$name!
   
Я умею пересылать сообщения из каналов в Telegram и фильтровать их по ключевым словам.

${getHelpInstruction().text}"""
                .trimIndent()
        )
    }

    /**
     * TODO: как-то запариться с форматированием бы, а то не очень красиво
     */
    private fun getHelpInstruction(): BotResponse {
        return BotResponse(
            """Мои команды:
• /subscribe `@имя_канала` - подписаться на новости из канала

• /addPreference `ключевое_слово` `+ или -` - включить фильтр новостей по ключевому слову.
Знак "+" добавит включающий фильтр, а знак "-" добавит исключающий фильтр

• /removePreference `ключевое_слово` - отключить фильтр новостей по ключевому слову

• /unsubscribe `@имя_канала` - отписаться от канала"""
                .trimIndent().replace("\t", "")
        )
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun subscribeUser(messageText: String, user: User): BotResponse {
        return BotResponse("Подписка на канал")
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun addPreference(messageText: String, user: User): BotResponse {
        return BotResponse("Добавляем предпочтение")
    }

    /**
     * Регистрирует подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun removePreference(messageText: String, user: User): BotResponse {
        return BotResponse("Убираем предпочтение")
    }

    /**
     * Отменяет подписку пользователя на канал
     * TODO: сделать начинку
     */
    private fun unsubscribeUser(messageText: String, user: User): BotResponse {
        return BotResponse("Отмена подписки на канал")
    }

    /**
     * Формирует ответное сообщение бота и отправляет его в указанный чат
     */
    private fun sendResponse(chatId: Long, botResponse: BotResponse) {
        val responseMessage = SendMessage(chatId.toString(), botResponse.text)
        responseMessage.enableMarkdown(true)
        responseMessage.replyMarkup = botResponse.buttonsReplyMarkup
        execute(responseMessage)
    }

}
