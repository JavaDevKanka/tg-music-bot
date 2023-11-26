package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.konkatenazia.tgmusicbot.dto.enums.InsultResponses;
import ru.konkatenazia.tgmusicbot.dto.word.WordDTO;
import ru.konkatenazia.tgmusicbot.repository.SwearWordRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageProcessingService {

    private final SwearWordRepository swearWordRepository;

    public String checkForBadWords(String messageText) {
        String[] words = messageText.toLowerCase(Locale.ROOT).split("[,\\s]+");
        if (swearWordRepository.existsByWordInIgnoreCase(words)) {
            return InsultResponses.getRandomResponse().getResponse();
        }
        return null;
    }

    public boolean inEnglishKeyLayout(String text) {
        String regex = "[a-zA-Z\\s\\p{Punct}]+";
        log.info("Английская ли раскладка {} - {}", text, text.matches(regex));
        return text.matches(regex);
    }

    public boolean isEnglishWord(String wordForCheck) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/";

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(wordForCheck);
        List<String> splittedWords = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            splittedWords.add(word);
        }

        for (String word : splittedWords) {
            try {
                ResponseEntity<WordDTO[]> response = restTemplate.getForEntity(url + word, WordDTO[].class);
                WordDTO[] insultWords = response.getBody();

                if (insultWords != null && response.getStatusCode().is2xxSuccessful()) {
                    long count = Arrays.stream(insultWords)
                            .filter(elem -> elem.getMeanings() != null && !elem.getMeanings().isEmpty())
                            .filter(elem -> elem.getMeanings().get(0).getDefinitions() != null && !elem.getMeanings().get(0).getDefinitions().isEmpty())
                            .filter(elem -> elem.getMeanings().get(0).getDefinitions().get(0).getDefinition() != null)
                            .map(elem -> elem.getMeanings().get(0).getDefinitions().get(0).getDefinition())
                            .distinct()
                            .count();

                    log.info("Количество строк: {}", count);
                    return count > 2;
                }
            } catch (HttpClientErrorException.NotFound ex) {
                log.info("Слово не найдено: {}", word);
            }
        }

        return false;
    }


    @SneakyThrows
    public String invertKeyboardLayout(String text) {
        String url = "https://raskladki.net.ru/post.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String params = "text=" + text + "&lang=" + "eng2rus";
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

