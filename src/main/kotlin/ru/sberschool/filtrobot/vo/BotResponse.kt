package ru.sberschool.filtrobot.vo

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class BotResponse(
    val text: String,
    private val buttons: List<List<BotButton>>? = null
) {
    val buttonsReplyMarkup: InlineKeyboardMarkup
        get() {
            val markup = InlineKeyboardMarkup()

            markup.keyboard = buttons?.map { rowButtons ->
                val row = mutableListOf<InlineKeyboardButton>()
                rowButtons.forEach { rowButton -> row.add(InlineKeyboardButton(rowButton.text)) }
                row
            } ?: emptyList()

            return markup
        }
}

enum class BotButton(val text: String) {
    OK("Да"),
    CANCEL("Нет"),
    SUBSCRIBE("Подписаться на канал")
}
