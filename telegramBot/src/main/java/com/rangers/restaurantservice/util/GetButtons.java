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
        button1.setCallbackData("owner1");
        button2.setCallbackData("owner2");
        button3.setCallbackData("owner3");
        button4.setCallbackData("owner4");
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

    public static List<List<InlineKeyboardButton>> getListsProductCatalogOwnerMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Working with categories");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Working with product");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Adding product");
        button1.setCallbackData("WorkingWithCategories");
        button2.setCallbackData("WorkingWithProduct");
        button3.setCallbackData("AddingProduct");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowInline.add(button3);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline);
        return rowsInline;
    }

    public static List<List<InlineKeyboardButton>> getListsWorkingWithCategoriesOwnerMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Adding category");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Show deleted");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Change/Delete");
        button1.setCallbackData("WorkingWithCategories1");
        button2.setCallbackData("WorkingWithCategories2");
        button3.setCallbackData("WorkingWithCategories3");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowInline.add(button3);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline);
        return rowsInline;
    }

    public static List<List<InlineKeyboardButton>> getListsProductOwnerMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Product categories");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Search by name");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Show all");
        button1.setCallbackData("WorkingWithProduct1");
        button2.setCallbackData("WorkingWithProduct2");
        button3.setCallbackData("WorkingWithProduct3");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowInline.add(button3);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline);
        return rowsInline;
    }

    public static List<List<InlineKeyboardButton>> getListsNewsletterMenu() {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Create new newsletter");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Archive newsletter");
        button1.setCallbackData("Newsletter1");
        button2.setCallbackData("Newsletter2");
        rowInline1.add(button1);
        rowInline1.add(button2);
        rowsInline.add(rowInline1);
        return rowsInline;
    }

}
