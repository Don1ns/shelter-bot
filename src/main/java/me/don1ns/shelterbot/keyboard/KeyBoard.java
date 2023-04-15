package me.don1ns.shelterbot.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import me.don1ns.shelterbot.listener.TelegramBotUpdateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс с методами отображения меню для пользователя в виде клавиатуры
 * @author Riyaz Karimullin
 */
@Component
public class KeyBoard {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    @Autowired
    private TelegramBot telegramBot;

    /**
     * Метод отображает меню, где выбирается приют.
     * @param chatId
     */
    public void chooseMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                "Кошачий", "Собачий");
        sendResponseMenu(chatId, replyKeyboardMarkup, "Выберите приют в меню ниже.");
    }

    /**
     * Метод отображает главное меню приюта.
     * @param chatId
     */
    public void shelterMainMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"Узнать информацию о приюте", "Как взять животного из приюта"},
                new String[]{"Прислать отчет о питомце", "Связаться с волонтером"});
        sendResponseMenu(chatId, replyKeyboardMarkup, "Ниже представлено главное меню приюта.");
    }

    /**
     * Метод отображает меню, с информацией о приюте.
     * @param chatId
     */
    public void shelterInfoMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                "Информация о приюте",
                "Рекомендации о технике безопасности на территории приюта");
        replyKeyboardMarkup.addRow(new KeyboardButton("Связаться с волонтером"),
                new KeyboardButton("Отправить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow("Главное меню");
        sendResponseMenu(chatId, replyKeyboardMarkup, "Вы можете получить информацию о приюте в меню.");
    }

    /**
     * Метод отображает меню, с информацией о том, как взять питомца из приюта.
     * @param chatId
     */
    public void shelterInfoHowAdoptPetMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup("Список рекомендаций",
                "Список необходимых документов");
        replyKeyboardMarkup.addRow(new KeyboardButton("Связаться с волонтером"),
                new KeyboardButton("Отправить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow("Главное меню");
        sendResponseMenu(chatId, replyKeyboardMarkup, "Информация о том, как взять животное из приюта");
    }

    /**
     * Метод принимает клавиатуру и текст, и отправляет ответ в чат по chatId.
     * @param chatId
     * @param replyKeyboardMarkup
     * @param text
     */
    public void sendResponseMenu(long chatId, ReplyKeyboardMarkup replyKeyboardMarkup, String text) {
        SendMessage sendMessage = new SendMessage(
                chatId, text).replyMarkup(replyKeyboardMarkup.resizeKeyboard(true));
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
