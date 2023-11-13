package ru.konkatenazia.tgmusicbot.keyboards;

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
        message.setText("main keyboard");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Получить случайную музыку");
        inlineKeyboardButton.setCallbackData("/getRandomMusic");

        firstRow.add(inlineKeyboardButton);
        rowsInLine.add(firstRow);
        markupInline.setKeyboard(rowsInLine);

        message.setReplyMarkup(markupInline);
        return message;
    }
}
