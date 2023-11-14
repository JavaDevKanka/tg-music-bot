package ru.konkatenazia.tgmusicbot.services;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.dto.enums.InsultResponses;
import ru.konkatenazia.tgmusicbot.repository.SwearWordRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.util.Locale;

@Component
public record SwearWordService(
        SwearWordRepository swearWordRepository,
        BotHeart botHeart
) {

    public void checkForBadWords(Message message) {
        var chatId = message.getChatId();
        var messageText = message.getText();
        var messageId = message.getMessageId();
        String[] words = messageText.toLowerCase(Locale.ROOT).split("[,\\s]+");
        for (String word : words) {
            if (swearWordRepository.existsByWord(word)) {
                botHeart.sendMessage(chatId, InsultResponses.getRandomResponse().getResponse(), messageId);
            }
        }
    }
}
