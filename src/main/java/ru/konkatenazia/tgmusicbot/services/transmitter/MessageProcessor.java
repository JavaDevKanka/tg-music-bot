package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.services.MessageProcessingService;

@Component
@RequiredArgsConstructor
public class MessageProcessor {

    private final MessageProcessingService messageProcessingService;

    public void processMessage(Message message) {
        if (message.getText() != null) {
            var chatId = message.getChatId();
            var messageText = message.getText();
            var messageId = message.getMessageId();
            messageProcessingService.checkForBadWords(chatId, messageText, messageId);
            messageProcessingService.checkKeyboardLayoutIsCorrectly(chatId, messageText, messageId);
        }
    }


}
