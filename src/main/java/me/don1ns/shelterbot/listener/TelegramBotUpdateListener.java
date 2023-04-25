package me.don1ns.shelterbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import me.don1ns.shelterbot.constant.ShelterType;
import me.don1ns.shelterbot.keyboard.KeyBoard;
import me.don1ns.shelterbot.model.CatOwners;
import me.don1ns.shelterbot.model.Context;
import me.don1ns.shelterbot.model.DogOwner;
import me.don1ns.shelterbot.constant.ButtonCommand;
import me.don1ns.shelterbot.service.CatOwnersService;
import me.don1ns.shelterbot.service.ContextService;
import me.don1ns.shelterbot.service.DogOwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Класс обработки сообщений
 *
 * @author Riyaz Karimullin
 */
@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final TelegramBot telegramBot;
    private final KeyBoard keyBoard;
    private final ContextService contextService;
    private final CatOwnersService catOwnersService;
    private final DogOwnerService dogOwnerService;
    @Value("${volunteer-chat-id}")
    private Long volunteerChatId;

    public TelegramBotUpdateListener(TelegramBot telegramBot, KeyBoard keyBoard,
                                     ContextService contextService, CatOwnersService catOwnersService,
                                     DogOwnerService dogOwnerService) {
        this.telegramBot = telegramBot;
        this.keyBoard = keyBoard;
        this.contextService = contextService;
        this.catOwnersService = catOwnersService;
        this.dogOwnerService = dogOwnerService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод предназначенный для switch-case,
     * который принимает текст сообщения пользователя и сравнивает со значениями enum класса ButtonCommand
     *
     * @param buttonCommand
     * @return
     */
    public static ButtonCommand parse(String buttonCommand) {
        ButtonCommand[] values = ButtonCommand.values();
        for (ButtonCommand command : values) {
            if (command.getCommand().equals(buttonCommand)) {
                return command;
            }
        }
        return null;
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
                Contact contact = update.message().contact();


                switch (parse(text)) {
                    case START -> {
                        if (contextService.getByChatId(chatId).isEmpty()) {
                            sendResponseMessage(chatId, "Привет! Я могу показать информацию о приютах," +
                                    "как взять животное из приюта и принять отчет о питомце");
                            Context context = new Context();
                            context.setChatId(chatId);
                            contextService.saveContext(context);
                        }
                        keyBoard.chooseMenu(chatId);
                    }
                    case CAT -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (catOwnersService.getByChatId(chatId).isEmpty()) {
                                CatOwners catOwner = new CatOwners();
                                catOwner.setChatId(chatId);
                                catOwnersService.create(catOwner);
                                context.setCatOwner(catOwner);
                            }
                            context.setShelterType(ShelterType.CAT);
                            contextService.saveContext(context);
                            sendResponseMessage(chatId, "Вы выбрали кошачий приют.");
                            keyBoard.shelterMainMenu(chatId);
                        }

                    }
                    case DOG -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (dogOwnerService.getByChatId(chatId).isEmpty()) {
                                DogOwner dogOwner = new DogOwner();
                                dogOwner.setChatId(chatId);
                                dogOwnerService.save(dogOwner);
                                context.setDogOwner(dogOwner);
                            }
                            context.setShelterType(ShelterType.DOG);
                            contextService.saveContext(context);
                            sendResponseMessage(chatId, "Вы выбрали собачий приют.");
                            keyBoard.shelterMainMenu(chatId);
                        }
                    }
                    case MAIN_MENU -> {
                        keyBoard.shelterMainMenu(chatId);
                    }
                    case SHELTER_INFO_MENU -> {
                        keyBoard.shelterInfoMenu(chatId);
                    }
                    case SHELTER_INFO -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(ShelterType.CAT)) {
                                sendResponseMessage(chatId, """
                                        Информация о кошачем приюте - ...
                                        Рекомендации о технике безопасности на территории кошачего приюта - ...
                                        Контактные данные охраны - ...
                                        """);
                            } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                sendResponseMessage(chatId, """
                                        Информация о собачем приюте - ...
                                        Рекомендации о технике безопасности на территории собачего приюта - ...
                                        Контактные данные охраны - ...
                                        """);
                            }
                        }
                    }
                    case SHELTER_ADDRESS_SCHEDULE -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(ShelterType.CAT)) {
                                sendResponseMessage(chatId, """
                                        Адрес кошачего приюта - ...
                                        График работы - ...
                                        """);
                            } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                sendResponseMessage(chatId, """
                                        Адрес кошачего приюта - ...
                                        График работы - ...
                                        """);
                            }
                        }
                    }
                    case VOLUNTEER -> {
                        sendResponseMessage(chatId, "Мы передали ваше сообщение волонтеру. " +
                                "Если у вас закрытый профиль отправьте контактные данные," +
                                "с помощью кнопки в меню - Отправить контактные данные");
                        sendForwardMessage(chatId, messageId);
                    }
                    case HOW_ADOPT_PET_INFO -> {
                        keyBoard.shelterInfoHowAdoptPetMenu(chatId);
                    }
                    case RECOMMENDATIONS_LIST -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(ShelterType.CAT)) {
                                sendResponseMessage(chatId, """
                                        Правила знакомства с животным - ...
                                        Список рекомендаций - ...
                                        Список причин отказа в выдаче животного - ...
                                        """);
                            } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                sendResponseMessage(chatId, """
                                        Правила знакомства с животным - ...
                                        Список рекомендаций - ...
                                        Советы кинолога по первичному общению с собакой - ...
                                        Рекомендации по проверенным кинологам для дальнейшего обращения к ним
                                        Список причин отказа в выдаче животного - ...
                                        """);
                            }
                        }
                    }
                    case DOCUMENTS_LIST -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(ShelterType.CAT)) {
                                sendResponseMessage(chatId,
                                        "Для взятия кота из приюта необходимы такие документы: ...");
                            } else if (context.getShelterType().equals(ShelterType.DOG)) {
                                sendResponseMessage(chatId,
                                        "Для взятия собаки из приюта необходимы такие документы: ...");
                            }
                        }
                    }
                    case null -> {
                        if (contextService.getByChatId(chatId).isPresent()) {
                            Context context = contextService.getByChatId(chatId).get();
                            if (context.getShelterType().equals(
                                    ShelterType.CAT) && update.message() != null && contact != null) {
                                CatOwners catOwner = context.getCatOwner();
                                catOwner.setPhone(contact.phoneNumber());
                                catOwner.setName(contact.firstName());
                                catOwnersService.update(catOwner);
                            } else if (context.getShelterType().equals(
                                    ShelterType.DOG) && update.message() != null && contact != null) {
                                DogOwner dogOwner = context.getDogOwner();
                                dogOwner.setPhone(contact.phoneNumber());
                                dogOwner.setName(contact.firstName());
                                dogOwnerService.save(dogOwner);
                            }
                            sendForwardMessage(chatId, messageId);
                            sendResponseMessage(chatId, "Мы получили ваши контактные данные");

                        }
                    }
                    default -> sendResponseMessage(chatId, "Неизвестная команда!");
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
        ForwardMessage forwardMessage = new ForwardMessage(volunteerChatId, chatId, messageId);
        SendResponse sendResponse = telegramBot.execute(forwardMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}
