package ru.konkatenazia.tgmusicbot.services.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.konkatenazia.tgmusicbot.services.FileOperationService;
import ru.konkatenazia.tgmusicbot.services.MusicService;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicAsyncService {

    private final FileOperationService fileOperationService;
    private final MusicService musicService;
    @Async("saveMusicAsync")
    public CompletableFuture<Void> saveMusicAsync(MultipartFile[] multipartFiles) {
        var filePaths = fileOperationService.saveArchivesAndGetAbsPaths(multipartFiles);
        var extractedFilesPath = fileOperationService.extractArchivesAndGetAbsPaths(filePaths);
        return CompletableFuture.completedFuture(null)
                .thenRun(() -> log.info("Cохранение музыки асинхронно завершено!"))
                .thenRun(() -> musicService.extractMusicDataToDb(extractedFilesPath))
                .thenRun(() ->fileOperationService.removeUnpackedArchives(filePaths));
    }
}
