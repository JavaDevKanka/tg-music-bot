package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.konkatenazia.tgmusicbot.dto.enums.InsultResponses;
import ru.konkatenazia.tgmusicbot.repository.SwearWordRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageProcessingService {

    private final SwearWordRepository swearWordRepository;
    private final BotHeart botHeart;

    public void checkForBadWords(Long chatId, String messageText, Integer messageId) {
        String[] words = messageText.toLowerCase(Locale.ROOT).split("[,\\s]+");
        if (swearWordRepository.existsByWordInIgnoreCase(words)) {
            botHeart.sendMessage(chatId, InsultResponses.getRandomResponse().getResponse(), messageId);
        }
    }

    @SneakyThrows
    public void checkKeyboardLayoutIsCorrectly(Long chatId, String messageText, Integer messageId) {
        if (detectLanguage(messageText).equals("Английский")) {
            var invertedText = invertKeyboardLayout(messageText, "eng2rus");
            botHeart.sendMessage(chatId, invertedText, messageId);
            checkForBadWords(chatId, invertedText, messageId);
        }
    }

    public String detectLanguage(String text) {
        String regex = "[a-zA-Z]+";
        regex += "|[а-яА-ЯёЁ]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String language = matcher.group();
            return getLanguageName(language);
        }

        return "Не удалось определить язык";
    }

    private String getLanguageName(String language) {
        if (language.matches("[a-zA-Z]+")) {
            return "Английский";
        } else if (language.matches("[а-яА-ЯёЁ]+")) {
            return "Русский";
        } else {
            return "Неизвестный язык";
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

