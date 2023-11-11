package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.konkatenazia.tgmusicbot.services.processors.BotProcessor;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final BotProcessor botProcessor;

    public void sendAudioMessage(String chatId) {
        botProcessor.sendMessage(chatId, "someMessage");
    }

}
