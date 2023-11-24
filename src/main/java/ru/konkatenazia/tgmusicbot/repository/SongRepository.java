package ru.konkatenazia.tgmusicbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.konkatenazia.tgmusicbot.model.Song;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
    Song getSongBySongName(String songName);

    @Query(value = "select path_to_file from song order by random() limit 1", nativeQuery = true)
    String getRandomSong();
}
