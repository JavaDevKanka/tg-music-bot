package ru.konkatenazia.tgmusicbot.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.konkatenazia.tgmusicbot.services.MusicService;

@Component
@RequiredArgsConstructor
public class MainEventProcessor {
    private final MusicService musicService;
    private final BotProcessor botProcessor;

    @EventListener
    public void processUpdate(Update update) {
        if (update.hasMessage()) {
            var chatId = String.valueOf(update.getMessage().getChatId());
            var message = update.getMessage().getText();
            botProcessor.sendMessage(chatId, message);
        } else if (update.hasCallbackQuery()) {
            var callbackChatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            musicService.sendAudioMessage(callbackChatId);
        }

    }
}
