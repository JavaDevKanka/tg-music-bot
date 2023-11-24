package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.konkatenazia.tgmusicbot.mapper.UserMapper;
import ru.konkatenazia.tgmusicbot.model.ChatUser;
import ru.konkatenazia.tgmusicbot.repository.ChatUserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ChatUserRepository chatUserRepository;
    private final UserMapper userMapper;

    public boolean isUserSaved(User user) {
        var findUser = chatUserRepository.findById(user.getId());
        return findUser.isPresent();
    }

    public ChatUser saveUser(User user) {
        var chatUser = userMapper.toChatUser(user);
        return chatUserRepository.save(chatUser);
    }
}
