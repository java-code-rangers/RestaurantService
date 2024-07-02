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

    public static List<List<InlineKeyboardButton>> getListsStartMenuOwner() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Product Catalog");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Newsletters");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Orders");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Managers");
        button1.setCallbackData("start1");
        button2.setCallbackData("start2");
        button3.setCallbackData("start3");
        button4.setCallbackData("start4");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowInline.add(button3);
        rowInline.add(button4);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline);
        return rowsInline;
    }

    public static List<List<InlineKeyboardButton>> getListsProductMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Categories");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Search by name");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Show all");
        button1.setCallbackData("Product1");
        button2.setCallbackData("Product2");
        button3.setCallbackData("Product3");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowInline.add(button3);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline);
        return rowsInline;
    }

    public static List<List<InlineKeyboardButton>> getListsOrderMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Active orders");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Order archive");
        button1.setCallbackData("Product1");
        button2.setCallbackData("Product2");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowsInline.add(rowInline1);
        return rowsInline;
    }
}
