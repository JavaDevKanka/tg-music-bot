package ru.konkatenazia.tgmusicbot.services.basebot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
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

    public void sendMessage(Long chatId, String message, Integer messageId) {
        SendMessage sMessage = new SendMessage();
        sMessage.setReplyToMessageId(messageId);
        sMessage.setChatId(chatId);
        sMessage.setText(message);
        try {
            execute(sMessage);
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

    public void sendMessage(SendMessage message, Integer messageId) {
        message.setReplyToMessageId(messageId);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(chatId);
        sMessage.setText(message);
        try {
            execute(sMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public File executeGetFile(GetFile file) {
        File fileResult = null;
        try {
            fileResult = execute(file);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
        return fileResult;
    }

    public void sendAudio(Long chatId, InputFile inputFile) {
        SendAudio audio = new SendAudio();
        audio.setAudio(inputFile);
        audio.setChatId(chatId);
        try {
            execute(audio);
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
