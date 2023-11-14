package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.Music;

import java.util.UUID;

public interface MusicRepository extends JpaRepository<Music, UUID> {
}
