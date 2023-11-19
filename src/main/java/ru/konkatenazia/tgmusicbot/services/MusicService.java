package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.konkatenazia.tgmusicbot.model.Music;
import ru.konkatenazia.tgmusicbot.model.Song;
import ru.konkatenazia.tgmusicbot.repository.MusicRepository;
import ru.konkatenazia.tgmusicbot.repository.SongRepository;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final SongRepository songRepository;

    public InputFile getRandomMusic() {
        var randomSongPath = songRepository.getRandomSong();
        InputFile file = new InputFile();
        file.setMedia(new File(randomSongPath));
        return file;
    }

    @Transactional
    public void extractMusicDataToDb(List<String> musicPaths) {
        musicPaths.forEach(System.out::println);
        for (String musicPath : musicPaths) {
            extractMetadata(musicPath);
        }
    }

    public void extractMetadata(String filePath) {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            Tag tag = audioFile.getTag();
            var author = tag.getFirst(FieldKey.ARTIST);
            var genre = tag.getFirst(FieldKey.GENRE);
            var songName = tag.getFirst(FieldKey.TITLE);
            var album = tag.getFirst(FieldKey.ALBUM);
            var song = Song.builder();
            var songFromDb = songRepository.getBySongName(songName);
            if (songFromDb == null) {
                song.songName(songName)
                        .album(album)
                        .pathToFile(filePath);
                var music = Music.builder();
                var musicFromDb = musicRepository.getFirstByAuthorAndGenre(author, genre);
                if (musicFromDb != null) {
                    music.author(musicFromDb.getAuthor())
                            .genre(musicFromDb.getGenre());
                } else {
                    music.author(author)
                            .genre(genre);
                }
                var savedMusic = musicRepository.save(music.build());
                song.music(savedMusic);
                songRepository.save(song.build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}