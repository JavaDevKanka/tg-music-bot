package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
