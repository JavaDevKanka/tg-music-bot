package ru.konkatenazia.tgmusicbot.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.konkatenazia.tgmusicbot.model.Music;
import ru.konkatenazia.tgmusicbot.model.Song;
import ru.konkatenazia.tgmusicbot.repository.MusicRepository;
import ru.konkatenazia.tgmusicbot.repository.SongRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;
import ru.konkatenazia.tgmusicbot.services.fileoperation.FileOperationService;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {

    private final BotHeart botHeart;
    private final MusicRepository musicRepository;
    private final SongRepository songRepository;
    private final FileOperationService fileOperationService;

    @Transactional
    public void unpackAndSaveMusic(Message message) {

        var archivePathAndName = fileOperationService.saveArchiveFileAndGetFileName(message);
        var songPathAndName = fileOperationService.extractMp3FromArchiveAndGetPathAndNameOfFile(archivePathAndName);
        botHeart.sendMessage(message.getChatId(), songPathAndName);
        try {
            AudioFile audioFile = AudioFileIO.read(new File(songPathAndName));
            Tag tag = audioFile.getTag();

            String title = tag.getFirst(FieldKey.TITLE);
            String author = tag.getFirst(FieldKey.ARTIST);
            String genre = tag.getFirst(FieldKey.GENRE);

            log.info(title + " : title");
            log.info(author + " : author");
            log.info(genre + " : genre");

            Music music = new Music();
            music.setAuthor(author);
            music.setGenre(genre);

            var savedMusic = musicRepository.save(music);

            Song song = new Song();
            song.setSongName(title);
            song.setMusic(savedMusic);
            songRepository.save(song);


        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Не удалось получить метаданные из песни !");
        }
    }


}