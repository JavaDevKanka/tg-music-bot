package ru.konkatenazia.tgmusicbot.services.transmitter;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.repository.SwearWordRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.util.Locale;

@Component
public record MessageProcessor(
        BotHeart botHeart,
        SwearWordRepository swearWordRepository) {

    public void processMessage(Message message) {
        var chatId = message.getChatId();
        var messageText = message.getText();

        if (containsSwearWord(messageText)) {
            botHeart.sendMessage(chatId, "Не сквернословь, какашка!!!");
        }

    }

    public boolean containsSwearWord(String messageText) {
        String[] words = messageText.toLowerCase(Locale.ROOT).split("[,\\s]+");
        for (String word : words) {
            if (swearWordRepository.existsByWord(word)) {
                return true;
            }
        }
        return false;
    }
}
