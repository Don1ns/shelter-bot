package me.don1ns.shelterbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import me.don1ns.shelterbot.keyboard.KeyBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Класс обработки сообщений
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

    public TelegramBotUpdateListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод, позволяющий отслеживать и организовывать весь процесс общения с пользователем.
     * @param updates
     */
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Handles update: {}", update);
                Message message = update.message();
                Long chatId = message.chat().id();
                String text = message.text();

                switch (text) {
                    case "/start" -> {
                        //TODO: сделать проверку на повторное обращение
                        sendResponseMessage(chatId, "Привет!");
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
                        //TODO: дописать информацию о приютах
                        if (isCatShelter) {
                            sendResponseMessage(chatId, "Информация о кошачем приюте ...");
                        } else {
                            sendResponseMessage(chatId, "Информация о собачем приюте ...");
                        }
                    }
                    case "Рекомендации о технике безопасности на территории приюта" -> {
                        //TODO: дописать информацию о технике безопасности на территории приютов
                        if (isCatShelter) {
                            sendResponseMessage(chatId, "Рекомендации о технике безопасности кошачего приюта ...");
                        } else {
                            sendResponseMessage(chatId, "Рекомендации о технике безопасности собачего приюта ...");
                        }
                    }
                    case "Позвать волонтера" -> {
                        //TODO: сделать обработку
                        sendResponseMessage(chatId, "Позвать волонтера ...");
                    }
                    case "Отправить контактные данные" -> {
                        //TODO: сделать обработку
                        sendResponseMessage(chatId, "Отправить контактные данные ...");
                    }
                    case "Как взять животного из приюта" -> {
                        keyBoard.shelterInfoHowAdoptPetMenu(chatId);
                    }
                    case "Список рекомендаций" -> {
                        //TODO: дописать список рекомендаций для каждого приюта
                        if(isCatShelter){
                            sendResponseMessage(chatId, "Список рекомендаций, для взятия кота из приюта ...");
                        } else {
                            sendResponseMessage(chatId, "Список рекомендаций, для взятия собаки из приюта ...");
                        }
                    }
                    case "Список необходимых документов" -> {
                        //TODO: дописать список документов для каждого приюта
                        if(isCatShelter){
                            sendResponseMessage(chatId, "Список документов для кота ...");
                        } else {
                            sendResponseMessage(chatId, "Список документов для собаку ...");
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
}
