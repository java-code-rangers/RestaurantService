package com.rangers.restaurantservice.util;

import com.rangers.restaurantservice.util.headers.MenuHeader;
import com.rangers.restaurantservice.util.message.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class BotUtils {
    public static SendMessage sendMessage(String chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);
        return sendMessage;
    }

    private static SendMessage sendMenu(String chatId, List<List<InlineKeyboardButton>> rowsInline, String header) {
        SendMessage message = sendMessage(chatId, header);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();

        markupKeyboard.setKeyboard(rowsInline);
        message.setReplyMarkup(markupKeyboard);

        return message;
    }

    /**
     * Sends the start menu to the user.
     *
     * @param chatId The chat identifier to which the start menu is sent.
     * @author Viktor
     */
    public static SendMessage sendStartMenu(String chatId) {
        return sendMenu(chatId, GetButtons.getListsStartMenu(), MenuHeader.CHOOSE_ACTION);
    }

    public static SendPhoto sendStartMessage(String chatId) {
        return sendPhoto(chatId,"https://th.bing.com/th/id/OIG1.YnHNFmFk4OOEKc0Mec0y?pid=ImgGn", Message.START_MESSAGE);
    }

    private static SendPhoto sendPhoto(String chatId, String photoFileId, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(photoFileId));
        sendPhoto.setCaption(caption);
        return sendPhoto;
    }
}
