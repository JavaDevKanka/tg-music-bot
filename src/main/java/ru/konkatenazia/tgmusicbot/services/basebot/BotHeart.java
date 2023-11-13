package ru.konkatenazia.tgmusicbot.services.basebot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konkatenazia.tgmusicbot.config.BotConfig;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BotHeart extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void setCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/show", "Отобразит основную клавиатуру"));
        listOfCommands.add(new BotCommand("/music", "Отправит вам случайную музыку"));
        listOfCommands.add(new BotCommand("/categories", "Отобразит категории"));
        listOfCommands.add(new BotCommand("/help", "Инструкция по использованию бота"));
        commandsExecutor(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
    }

    @Override
    public void onUpdateReceived(Update update) {
        applicationEventPublisher.publishEvent(update);
    }

    public void commandsExecutor(SetMyCommands commands) {
        try {
            execute(commands);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(Long chatId, String message) {
        var stringId = String.valueOf(chatId);
        try {
            execute(new SendMessage(stringId, message));
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