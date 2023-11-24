package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.konkatenazia.tgmusicbot.mapper.SwearAccountingMapper;
import ru.konkatenazia.tgmusicbot.model.ChatUser;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;
import ru.konkatenazia.tgmusicbot.repository.ChatUserRepository;
import ru.konkatenazia.tgmusicbot.repository.SwearAccountingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SwearAccountingService {
    private final SwearAccountingRepository swearAccountingRepository;
    private final SwearAccountingMapper swearAccountingMapper;
    private final ChatUserRepository chatUserRepository;

    public SwearAccounting saveSwearAccounting(String message, User user, Long chatId, Boolean isGroupChat, Boolean isActive) {
        var chatUser = chatUserRepository.findById(user.getId());
        if (chatUser.isPresent()) {
            SwearAccounting swear = swearAccountingMapper.toSwearAccounting(chatUser.get(), message, chatId, isGroupChat, isActive);
            return swearAccountingRepository.save(swear);
        }
        log.info("Пользователь с id {} не найден в БД, ругательство не сохранено!", user.getId());
        throw new RuntimeException();
    }
}
