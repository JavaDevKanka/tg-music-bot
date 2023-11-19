package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.services.MessageProcessingService;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

@Component
@RequiredArgsConstructor
public class MessageProcessor {

    private final MessageProcessingService messageProcessingService;
    private final BotHeart botHeart;

    public void processMessage(Message message) {
        if (message.getText() != null) {
            var chatId = message.getChat().getId();
            var messageText = message.getText();
            var messageId = message.getMessageId();
            if (messageProcessingService.checkForBadWords(messageText) != null) {
                botHeart.sendMessage(chatId, messageProcessingService.checkForBadWords(messageText), messageId);
            }
            if (messageProcessingService.detectLanguage(messageText).equals("Английский") && !messageText.startsWith("/")) {
                botHeart.sendMessage(chatId, messageProcessingService.invertKeyboardLayout(messageText));
            }
        }
    }
}
