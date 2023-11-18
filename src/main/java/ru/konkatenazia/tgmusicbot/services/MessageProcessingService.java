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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @SneakyThrows
    public void checkKeyboardLayoutIsCorrectly(Message message) {
        var chatId = message.getChatId();
        var messageText = message.getText();
        var messageId = message.getMessageId();
        DetectLanguage.apiKey = apiKey;

        List<Result> results = DetectLanguage.detect(messageText);
        Result result = results.get(0);
        if (result.language.equals("en")) {
            var invertedText = invertKeyboardLayout(messageText, "eng2rus");
            List<Result> resultsInverted = DetectLanguage.detect(invertedText);
            if (!resultsInverted.isEmpty() && resultsInverted.get(0).language.equals("ru")) {
                botHeart.sendMessage(chatId, invertedText, messageId);
            }
        }
    }

    public String invertKeyboardLayout(String text, String direction) throws IOException {
        String url = "https://raskladki.net.ru/post.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String params = "text=" + text + "&lang=" + direction;
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(params.getBytes());
        os.flush();
        os.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}

