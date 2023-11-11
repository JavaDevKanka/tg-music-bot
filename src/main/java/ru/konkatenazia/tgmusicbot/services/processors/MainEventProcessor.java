package ru.konkatenazia.tgmusicbot.services.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.konkatenazia.tgmusicbot.services.MusicService;

@Component
@RequiredArgsConstructor
public class MainEventProcessor {
    private final MusicService musicService;

    @EventListener
    public void processUpdate(Update update) {
        var chatId = String.valueOf(update.getMessage().getChatId());
        var message = update.getMessage().getText();

        musicService.sendAudioMessage(chatId);
    }
}
