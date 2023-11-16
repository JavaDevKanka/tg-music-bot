package ru.konkatenazia.tgmusicbot.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.konkatenazia.tgmusicbot.repository.MusicRepository;
import ru.konkatenazia.tgmusicbot.repository.SongRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {

    private final BotHeart botHeart;
    private final MusicRepository musicRepository;
    private final SongRepository songRepository;

    private static final String MUSIC_ARCHIVE_PATH = "music-files/archive";
    private static final String UNPACKED_MUSIC_PATH = "music-files/music";

    public void unpackMusic(MultipartFile[] musicArchive) {
        for (MultipartFile archive : musicArchive) {
            try {
                byte[] bytes = archive.getBytes();
                File outputFile = new File(MUSIC_ARCHIVE_PATH, Objects.requireNonNull(archive.getOriginalFilename()));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("Не удалось сохранить файл " + archive.getName());
            }
        }

    }
}