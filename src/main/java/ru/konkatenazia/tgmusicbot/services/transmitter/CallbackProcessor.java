package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.konkatenazia.tgmusicbot.services.MusicService;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

@Component
@RequiredArgsConstructor
public class CallbackProcessor {

    private final MusicService musicService;
    private final BotHeart botHeart;

    public void processCallback(CallbackQuery callback) {

    }
}