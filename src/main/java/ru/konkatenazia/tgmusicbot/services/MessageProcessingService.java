package ru.konkatenazia.tgmusicbot.services;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.dto.enums.InsultResponses;
import ru.konkatenazia.tgmusicbot.repository.SwearWordRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.util.List;
import java.util.Locale;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageProcessingService {

    private final SwearWordRepository swearWordRepository;
    private final BotHeart botHeart;

    @Value("${API_KEY}")
    private String apiKey;

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

    public void checkKeyboardLayoutIsCorrectly(Message message) {
        var chatId = message.getChatId();
        var messageText = message.getText();
        var messageId = message.getMessageId();

        botHeart.sendMessage(chatId, isRussianText(messageText));
    }

    @SneakyThrows
    private String isRussianText(String text) {
        DetectLanguage.apiKey = apiKey;

        List<Result> results = DetectLanguage.detect(text);
        Result result = results.get(0);
        System.out.println(result.language);
        return "";
    }
}

