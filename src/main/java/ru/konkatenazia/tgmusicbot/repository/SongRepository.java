package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.konkatenazia.tgmusicbot.model.Song;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
    Song getBySongName(String songName);
}
