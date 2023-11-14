package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.SwearWord;

import java.util.UUID;

public interface SongRepository extends JpaRepository<SwearWord, UUID> {
}
