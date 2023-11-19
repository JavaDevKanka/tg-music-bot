package ru.konkatenazia.tgmusicbot.services.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.konkatenazia.tgmusicbot.services.FileOperationService;
import ru.konkatenazia.tgmusicbot.services.MusicService;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicAsyncService {

    private final FileOperationService fileOperationService;
    private final MusicService musicService;

    public void saveMusicAsync(MultipartFile[] multipartFiles) {
        var filePaths = new ArrayList<String>();

        for (MultipartFile multipartFile : multipartFiles) {
            var filePath = fileOperationService.saveArchiveAndGetAbsPath(multipartFile);
            filePaths.add(filePath);
        }

        var extractedFilesPath = fileOperationService.extractArchivesAndGetAbsPaths(filePaths);

        CompletableFuture.runAsync(() -> {
            log.info("Cохранение музыки асинхронно завершено!");
            musicService.extractMusicDataToDb(extractedFilesPath);
        }).thenRun(() -> {
            fileOperationService.removeUnpackedArchives(filePaths);
        });
    }
}
