package com.rangers.restaurantservice.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GetButtons {
    /**
     * Returns a list of buttons for the main menu.
     * @author Viktor
     * @return A list of button rows.
     */
    public static List<List<InlineKeyboardButton>> getListsStartMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Order in a cafe");
        InlineKeyboardButton button2 = new InlineKeyboardButton("My orders");
//        InlineKeyboardButton button3 = new InlineKeyboardButton("Virtual assistant");
        button1.setCallbackData("start1");
        button2.setCallbackData("start2");
//        button3.setCallbackData("start3");
        rowInline1.add(button1);
        rowInline1.add(button2);
//        rowInline.add(button3);
        rowsInline.add(rowInline1);
//        rowsInline.add(rowInline1);
        return rowsInline;
    }
}
