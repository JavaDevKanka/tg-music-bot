package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.mapper.UserMapper;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;
import ru.konkatenazia.tgmusicbot.repository.SwearAccountingRepository;
import ru.konkatenazia.tgmusicbot.services.MessageProcessingService;
import ru.konkatenazia.tgmusicbot.services.SwearAccountingService;
import ru.konkatenazia.tgmusicbot.services.UserService;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProcessor {

    private final MessageProcessingService messageProcessingService;
    private final BotHeart botHeart;
    private final SwearAccountingService swearAccountingService;
    private final UserService userService;

    public void processMessage(Message message) {
        if (message.getText() != null) {
            var user = message.getFrom();
            var chatId = message.getChatId();
            var messageText = message.getText();
            var messageId = message.getMessageId();
            if (!userService.isUserSaved(user)) {
                userService.saveUser(user);
            }

            if (messageProcessingService.checkForBadWords(messageText) != null) {
                swearAccountingService.saveSwearAccounting(messageText, user);
            }
            if (messageProcessingService.inEnglishKeyLayout(messageText) && !messageProcessingService.isEnglishWord(messageText) && !messageText.startsWith("http")) {
                log.info("Тут отправляются переведенные кракозябры, текст был {}.", messageText);
//                botHeart.sendMessage(chatId, messageProcessingService.invertKeyboardLayout(messageText), messageId);
            }
        }
    }
}