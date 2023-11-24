package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {

    Music getFirstByAuthorAndGenre(String author, String genre);
}
