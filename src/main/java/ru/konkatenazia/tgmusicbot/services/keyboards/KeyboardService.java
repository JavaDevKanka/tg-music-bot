package ru.konkatenazia.tgmusicbot.services.keyboards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyboardService {
    public SendMessage getMainKeyboard(Long chatId) {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Основная клавиатура");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Показать музыкальные категории");
        inlineKeyboardButton.setCallbackData("getMusicCategories");

        firstRow.add(inlineKeyboardButton);
        rowsInLine.add(firstRow);
        markupInline.setKeyboard(rowsInLine);

        message.setReplyMarkup(markupInline);
        return message;
    }

    public SendMessage getMusicCategoriesKeyboard(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        var rockButton = new InlineKeyboardButton();
        rockButton.setText("Rock");
        rockButton.setCallbackData("getRockCategory");

        var popButton = new InlineKeyboardButton();
        popButton.setText("Pop");
        popButton.setCallbackData("getPopCategory");

        var jazzButton = new InlineKeyboardButton();
        jazzButton.setText("Jazz");
        jazzButton.setCallbackData("getJazzCategory");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(rockButton);
        row1.add(popButton);
        row1.add(jazzButton);
        rows.add(row1);

        var indieButton = new InlineKeyboardButton();
        indieButton.setText("Indie");
        indieButton.setCallbackData("getIndieCategory");

        var rapButton = new InlineKeyboardButton();
        rapButton.setText("Rap");
        rapButton.setCallbackData("getRapCategory");

        var bluesButton = new InlineKeyboardButton();
        bluesButton.setText("Blues");
        bluesButton.setCallbackData("getBluesCategory");


        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(indieButton);
        row2.add(rapButton);
        row2.add(bluesButton);
        rows.add(row2);

        keyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите категорию музыки:");
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }
}
