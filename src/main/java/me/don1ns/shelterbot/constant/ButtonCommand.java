package me.don1ns.shelterbot.constant;

/**
 * Класс enum, в котором хранятся константы текстовых сообщения для работы с клавиатурой
 *
 * @author Riyaz Karimullin
 */
public enum ButtonCommand {

    START("/start"),
    CAT("Кошачий"),
    DOG("Собачий"),
    MAIN_MENU("Главное меню"),
    SHELTER_INFO_MENU("Узнать информацию о приюте"),
    HOW_ADOPT_PET_INFO("Как взять животного из приюта"),
    VOLUNTEER("Связаться с волонтером"),
    SHELTER_INFO("Общая информация"),
    SHELTER_ADDRESS_SCHEDULE("Адрес и график работы приюта"),
    RECOMMENDATIONS_LIST("Список рекомендаций и советов"),
    DOCUMENTS_LIST("Список необходимых документов"),
    SEND_CONTACT("Отправить контактные данные");


    private final String command;

    ButtonCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
