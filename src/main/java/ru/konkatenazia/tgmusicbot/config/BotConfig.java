package ru.konkatenazia.tgmusicbot.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {

    @Value("${telegram.bot-name}")
    String botName;
    @Value("${telegram.bot-token}")
    String botToken;
}
