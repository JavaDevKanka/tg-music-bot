package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.konkatenazia.tgmusicbot.model.ChatUser;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;
import ru.konkatenazia.tgmusicbot.repository.SwearAccountingRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleTaskService {
    private final BotHeart botHeart;
    private final SwearAccountingRepository swearAccountingRepository;

    @Scheduled(cron = "${cron.task.send.swear.result}", zone = "Europe/Moscow")
    @SchedulerLock(name = "SEND_SWEAR_RESULT", lockAtLeastFor = "30S", lockAtMostFor = "60S")
    public void sendSwearResults() {
        Map<Long, StringBuilder> resultToSend = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Доска почета сапожников дня : \n");

        var swears = swearAccountingRepository.findAllByCreatedEqualsAndIsGroupChatAndIsActive(LocalDate.now(), Boolean.TRUE, Boolean.TRUE);
        var users = swears.stream()
                .map(SwearAccounting::getChatUser)
                .distinct()
                .toList();

        for (ChatUser chatUser : users) {
            stringBuilder.append("@").append(chatUser.getUserName())
                    .append(" ")
                    .append("(")
                    .append(chatUser.getFirstName())
                    .append(")")
                    .append(":\n");
            var count = 1;
            for (SwearAccounting swearAccounting : swears) {
                if (swearAccounting.getChatUser().equals(chatUser)
                        && swearAccounting.getCreated().equals(LocalDate.now())) {
                    stringBuilder
                            .append(count)
                            .append(") ")
                            .append(swearAccounting.getSwearWord())
                            .append(" ;\n");
                    count++;
                    resultToSend.put(swearAccounting.getChatId(), stringBuilder);
                }
            }
        }
        resultToSend.forEach((chatId, message) -> {
            log.info("Результат из scheduler отправляется в чат {}", chatId);
            botHeart.sendMessage(chatId, message.toString());
            log.info("Результат из scheduler отправлен в чат {}", chatId);
        });

        swears.forEach(elem -> elem.setIsActive(Boolean.FALSE));
        swearAccountingRepository.saveAll(swears);
    }
}
