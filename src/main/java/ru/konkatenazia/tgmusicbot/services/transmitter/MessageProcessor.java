package ru.konkatenazia.tgmusicbot.services.transmitter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.services.MusicService;
import ru.konkatenazia.tgmusicbot.services.SwearWordService;

@Component
@RequiredArgsConstructor
public class MessageProcessor {

    private final SwearWordService swearWordService;
    private final MusicService musicService;

    public void processMessage(Message message) {
        if (message.getText() != null) {
            swearWordService.checkForBadWords(message);
        }

        if (message.getCaption() != null && message.hasDocument()) {
            if (message.getCaption().equals("Сохранить музыку")) {
                musicService.unpackAndSaveMusic(message);
            }

        }
    }


}
