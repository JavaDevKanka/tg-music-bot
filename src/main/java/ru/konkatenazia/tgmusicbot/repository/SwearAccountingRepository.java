package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;

public interface SwearAccountingRepository extends JpaRepository<SwearAccounting, Long> {
}
