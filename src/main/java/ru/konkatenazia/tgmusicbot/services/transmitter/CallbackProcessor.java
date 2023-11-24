package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.konkatenazia.tgmusicbot.services.MusicService;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;
import ru.konkatenazia.tgmusicbot.services.keyboards.KeyboardService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CallbackProcessor {
    private final BotHeart botHeart;
    private final KeyboardService keyboardService;

    public void processCallback(CallbackQuery callback) {
        if (callback.getMessage().hasText()) {
            var message = callback.getMessage();
            var callbackData = callback.getData();
            var chatId = message.getChatId();
            var messageId = message.getMessageId();

            //Блок клавиатур
            if (callbackData.equals("getMusicCategories")) {
                botHeart.sendMessage(keyboardService.getMusicCategoriesKeyboard(chatId), messageId);
            }


            //Блок кнопок
            if (callbackData.equals("getRockCategory")) {

            }
            if (callbackData.equals("getPopCategory")) {

            }
            if (callbackData.equals("getJazzCategory")) {

            }
            if (callbackData.equals("getIndieCategory")) {

            }
            if (callbackData.equals("getRapCategory")) {

            }
            if (callbackData.equals("getBluesCategory")) {

            }
        }
    }
}