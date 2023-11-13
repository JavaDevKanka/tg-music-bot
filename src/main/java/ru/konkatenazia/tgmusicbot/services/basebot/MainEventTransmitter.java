package ru.konkatenazia.tgmusicbot.services.basebot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.konkatenazia.tgmusicbot.keyboards.KeyboardService;
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

    @EventListener
    public void processUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            callbackProcessor.processCallback(update.getCallbackQuery());
        }

        if (update.hasMessage()) {
            messageProcessor.processMessage(update.getMessage());
            if (update.getMessage().hasText()) {
                var message = update.getMessage();
                var messageText = message.getText();

                Map<String, Runnable> commands = new HashMap<>();
                commands.put("/show", () -> botHeart.sendMessage(keyboardService.getMainKeyboard(message.getChatId())));
                commands.put("/music", () -> System.out.println("music"));
                commands.put("/categories", () -> System.out.println("categories"));
                commands.put("/help", () -> System.out.println("help"));
                if (commands.containsKey(messageText)) {
                    commands.get(messageText).run();
                }
            }

        }
    }
}
