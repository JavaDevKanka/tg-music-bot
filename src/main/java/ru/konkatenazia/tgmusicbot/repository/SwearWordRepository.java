package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.SwearWord;

public interface SwearWordRepository extends JpaRepository<SwearWord, Long> {

    boolean existsByWordInIgnoreCase(String[] word);
}
