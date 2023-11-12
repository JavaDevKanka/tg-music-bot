package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.konkatenazia.tgmusicbot.processors.BotProcessor;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final BotProcessor botProcessor;

    public void sendAudioMessage(String chatId) {
        botProcessor.sendAudio(chatId, new InputFile());
    }

}
