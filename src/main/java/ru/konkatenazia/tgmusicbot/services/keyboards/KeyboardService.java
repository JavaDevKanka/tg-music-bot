package ru.konkatenazia.tgmusicbot.services.keyboards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyboardService {

    public List<InlineKeyboardButton> getMainKeyboard() {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        var button = InlineKeyboardButton.builder()
                .text("Получить случайную музыку")
                .callbackData("randomSong").build();
        inlineKeyboardButtons.add(button);
        return inlineKeyboardButtons;
    }
}
