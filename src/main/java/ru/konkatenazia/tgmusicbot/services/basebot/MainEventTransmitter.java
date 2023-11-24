package ru.konkatenazia.tgmusicbot.services.basebot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.konkatenazia.tgmusicbot.services.MusicService;
import ru.konkatenazia.tgmusicbot.services.keyboards.KeyboardService;
import ru.konkatenazia.tgmusicbot.services.transmitter.CallbackProcessor;
import ru.konkatenazia.tgmusicbot.services.transmitter.MessageProcessor;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MainEventTransmitter {
    private final BotHeart botHeart;
    private final KeyboardService keyboardService;
    private final CallbackProcessor callbackProcessor;
    private final MessageProcessor messageProcessor;
    private final MusicService musicService;

    @EventListener
    public void processUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            callbackProcessor.processCallback(update.getCallbackQuery());
        }
        if (update.hasMessage()) {
            var message = update.getMessage();
            var chatId = message.getChat().getId();
            messageProcessor.processMessage(message);

            if (update.getMessage().hasText()) {
                var messageText = message.getText();
                Map<String, Runnable> commands = new HashMap<>();
                commands.put("/show", () -> botHeart.sendMessage(keyboardService.getMainKeyboard(chatId)));
                commands.put("/music", () -> botHeart.sendAudio(chatId, musicService.getRandomMusic()));
                for (String command : commands.keySet()) {
                    if (messageText.contains(command)) {
                        commands.get(command).run();
                        break;
                    }
                }
            }

        }
    }
}
