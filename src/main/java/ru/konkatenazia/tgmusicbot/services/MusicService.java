package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final BotHeart botHeart;
    public void sendAudioMessage(String chatId) {
        botHeart.sendAudio(chatId, new InputFile());
    }

}
