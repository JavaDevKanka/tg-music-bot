package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;

import java.time.LocalDate;
import java.util.List;

public interface SwearAccountingRepository extends JpaRepository<SwearAccounting, Long> {
    List<SwearAccounting> findAllByCreatedEqualsAndIsGroupChatAndIsActive(LocalDate created, Boolean isGroupChat, Boolean isActive);
}
