package ru.konkatenazia.tgmusicbot.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonPollType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konkatenazia.tgmusicbot.config.BotConfig;
import ru.konkatenazia.tgmusicbot.keyboards.KeyboardService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class BotProcessor extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final KeyboardService keyboardService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            List<BotCommand> listOfCommands = new ArrayList<>();
            listOfCommands.add(new BotCommand("/show", "Отобразит основную клавиатуру"));
            listOfCommands.add(new BotCommand("/music", "Отправит вам случайную музыку"));
            listOfCommands.add(new BotCommand("/categories", "Отобразит категории"));
            listOfCommands.add(new BotCommand("/help", "Инструкция по использованию бота"));
            try {
                execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
            } catch (TelegramApiException e) {
                log.error("Error setting bot's command list: " + e.getMessage());
            }

            String messageText = update.getMessage().getText();
            Map<String, Runnable> commands = new HashMap<>();
            commands.put("/show", () -> sendMessage(keyboardService.getMainKeyboard(update.getMessage().getChatId().toString())));
            commands.put("/music", () -> System.out.println("music"));
            commands.put("/categories", () -> System.out.println("categories"));
            commands.put("/help", () -> System.out.println("help"));
            if (commands.containsKey(update.getMessage().getText())) {
                commands.get(messageText).run();
            }
            applicationEventPublisher.publishEvent(update);
        } else {
            applicationEventPublisher.publishEvent(update);
        }
    }

    public void sendMessage(String chatId, String message) {
        try {
            execute(new SendMessage(chatId, message));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendAudio(String chatId, InputFile inputFile) {
        try {
            execute(new SendAudio(chatId, inputFile));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
