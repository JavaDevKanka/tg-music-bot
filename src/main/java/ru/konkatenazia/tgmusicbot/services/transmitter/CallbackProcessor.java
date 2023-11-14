package ru.konkatenazia.tgmusicbot.services.transmitter;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.konkatenazia.tgmusicbot.services.MusicService;

@Component
public record CallbackProcessor(
        MusicService musicService
) {

    public void processCallback(CallbackQuery callback) {

    }
}
