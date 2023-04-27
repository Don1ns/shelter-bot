package me.don1ns.shelterbot.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import me.don1ns.shelterbot.constant.ButtonCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Класс с методами отображения меню для пользователя в виде клавиатуры
 *
 * @author Riyaz Karimullin
 */
@Component
public class KeyBoard {
    private final Logger logger = LoggerFactory.getLogger(KeyBoard.class);
    private TelegramBot telegramBot;

    public KeyBoard(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод отображает меню, где выбирается приют.
     *
     * @param chatId
     */
    public void chooseMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                ButtonCommand.CAT.getCommand(), ButtonCommand.DOG.getCommand());
        sendResponseMenu(chatId, replyKeyboardMarkup, "Выберите приют в меню ниже.");
    }

    /**
     * Метод отображает главное меню приюта.
     *
     * @param chatId
     */
    public void shelterMainMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{ButtonCommand.SHELTER_INFO_MENU.getCommand(), ButtonCommand.HOW_ADOPT_PET_INFO.getCommand()},
                new String[]{ButtonCommand.SEND_REPORT.getCommand(), ButtonCommand.VOLUNTEER.getCommand()});
        sendResponseMenu(chatId, replyKeyboardMarkup, "Ниже представлено главное меню приюта. " +
                "Чтобы вернуться к выбору приюта, напишите команду /start");
    }

    /**
     * Метод отображает меню, с информацией о приюте.
     *
     * @param chatId
     */
    public void shelterInfoMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                ButtonCommand.SHELTER_INFO.getCommand(),
                ButtonCommand.SHELTER_ADDRESS_SCHEDULE.getCommand());
        replyKeyboardMarkup.addRow(new KeyboardButton(ButtonCommand.VOLUNTEER.getCommand()),
                new KeyboardButton(ButtonCommand.SEND_CONTACT.getCommand()).requestContact(true));
        replyKeyboardMarkup.addRow(ButtonCommand.MAIN_MENU.getCommand());
        sendResponseMenu(chatId, replyKeyboardMarkup, "Вы можете получить информацию о приюте в меню.");
    }

    /**
     * Метод отображает меню, с информацией о том, как взять питомца из приюта.
     *
     * @param chatId
     */
    public void shelterInfoHowAdoptPetMenu(long chatId) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(ButtonCommand.RECOMMENDATIONS_LIST.getCommand(),
                ButtonCommand.DOCUMENTS_LIST.getCommand());
        replyKeyboardMarkup.addRow(new KeyboardButton(ButtonCommand.VOLUNTEER.getCommand()),
                new KeyboardButton(ButtonCommand.SEND_CONTACT.getCommand()).requestContact(true));
        replyKeyboardMarkup.addRow(ButtonCommand.MAIN_MENU.getCommand());
        sendResponseMenu(chatId, replyKeyboardMarkup, "Информация о том, как взять животное из приюта");
    }

    /**
     * Метод принимает клавиатуру и текст, и отправляет ответ в чат по chatId.
     *
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
