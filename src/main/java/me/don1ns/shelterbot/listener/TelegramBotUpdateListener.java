package me.don1ns.shelterbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import me.don1ns.shelterbot.keyboard.KeyBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

//TODO: сделать обработку с получением контактных данных
/**
 * Класс обработки сообщений
 *
 * @author Riyaz Karimullin
 */
@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    @Autowired
    private final TelegramBot telegramBot;
    @Autowired
    private KeyBoard keyBoard;
    private boolean isCatShelter = false;
    private boolean isFirstAppeal = true;
    private static final long CHAT_ID_VOLUNTEER = 486713115L;

    public TelegramBotUpdateListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод, позволяющий отслеживать и организовывать весь процесс общения с пользователем.
     *
     * @param updates
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Handles update: {}", update);
                Message message = update.message();
                long chatId = message.chat().id();
                String text = message.text();
                int messageId = message.messageId();

                switch (text) {
                    case "/start" -> {
                        if(isFirstAppeal) {
                            sendResponseMessage(chatId, "Привет! Я могу показать информацию о приютах, как взять животное из приюта и принять отчет о питомце");
                            isFirstAppeal = false;
                        }
                        keyBoard.chooseMenu(chatId);
                    }
                    case "Кошачий" -> {
                        isCatShelter = true;
                        sendResponseMessage(chatId, "Вы выбрали кошачий приют.");
                        keyBoard.shelterMainMenu(chatId);
                    }
                    case "Собачий" -> {
                        isCatShelter = false;
                        sendResponseMessage(chatId, "Вы выбрали собачий приют.");
                        keyBoard.shelterMainMenu(chatId);
                    }
                    case "Главное меню" -> {
                        keyBoard.shelterMainMenu(chatId);
                    }
                    case "Узнать информацию о приюте" -> {
                        keyBoard.shelterInfoMenu(chatId);
                    }
                    case "Информация о приюте" -> {
                        if (isCatShelter) {
                            sendResponseMessage(chatId, """
                                    Информация о кошачем приюте - ...
                                    Расписание приюта - ...
                                    Адрес и схема проезда - ...
                                    Контактные данные охраны - ...
                                    """);
                        } else {
                            sendResponseMessage(chatId, """
                                    Информация о собачем приюте - ...
                                    Расписание приюта - ...
                                    Адрес и схема проезда - ...
                                    Контактные данные охраны - ...
                                    """);
                        }
                    }
                    case "Техника безопасности на территории приюта" -> {
                        if (isCatShelter) {
                            sendResponseMessage(chatId, "Рекомендации о технике безопасности на территории кошачего приюта - ...");
                        } else {
                            sendResponseMessage(chatId, "Рекомендации о технике безопасности на территории собачего приюта - ...");
                        }
                    }
                    case "Связаться с волонтером" -> {
                        sendResponseMessage(chatId, "Мы передали ваше сообщение волонтеру.");
                        sendForwardMessage(chatId, messageId);
                    }
                    case "Как взять животного из приюта" -> {
                        keyBoard.shelterInfoHowAdoptPetMenu(chatId);
                    }
                    case "Список рекомендаций и советов" -> {
                        if (isCatShelter) {
                            sendResponseMessage(chatId, """
                                    Правила знакомства с животным - ...
                                    Список рекомендаций - ...
                                    Список причин отказа в выдаче животного - ...
                                    """);
                        } else {
                            sendResponseMessage(chatId, """
                                    Правила знакомства с животным - ...
                                    Список рекомендаций - ...
                                    Советы кинолога по первичному общению с собакой - ...
                                    Рекомендации по проверенным кинологам для дальнейшего обращения к ним
                                    Список причин отказа в выдаче животного - ...
                                    """);
                        }
                    }
                    default -> sendResponseMessage(chatId, "Неизвестная команда!");
                    case "Список необходимых документов" -> {
                        if (isCatShelter) {
                            sendResponseMessage(chatId, "Для взятия кота из приюта необходимы такие документы: ...");
                        } else {
                            sendResponseMessage(chatId, "Для взятия собаки из приюта необходимы такие документы: ...");
                        }
                    }
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод отправки текстовых сообщений.
     *
     * @param chatId
     * @param text
     */
    public void sendResponseMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    /**
     * Метод пересылки сообщения волонтеру
     *
     * @param chatId
     * @param messageId
     */
    public void sendForwardMessage(long chatId, int messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(CHAT_ID_VOLUNTEER, chatId, messageId);
        SendResponse sendResponse = telegramBot.execute(forwardMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
