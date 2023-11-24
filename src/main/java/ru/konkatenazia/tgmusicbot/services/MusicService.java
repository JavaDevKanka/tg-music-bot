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
    public void saveMusicMetaData(String filePath) {
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            Tag tag = audioFile.getTag();
            var author = tag.getFirst(FieldKey.ARTIST);
            var genre = tag.getFirst(FieldKey.GENRE);
            var songName = tag.getFirst(FieldKey.TITLE);
            var album = tag.getFirst(FieldKey.ALBUM);
            Song songFromDb = songRepository.getSongBySongName(songName);
            if (songFromDb == null) {
                Music musicFromDb = musicRepository.getFirstByAuthorAndGenre(author, genre);
                if (musicFromDb == null) {
                    Music music = new Music();
                    music.setGenre(genre);
                    music.setAuthor(author);
                    var savedMusic = musicRepository.save(music);

                    Song song = new Song();
                    song.setSongName(songName);
                    song.setAlbum(album);
                    song.setPathToFile(filePath);
                    song.setMusic(savedMusic);
                    songRepository.save(song);
                } else {
                    Song song = new Song();
                    song.setSongName(songName);
                    song.setAlbum(album);
                    song.setPathToFile(filePath);
                    song.setMusic(musicFromDb);
                    songRepository.save(song);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}