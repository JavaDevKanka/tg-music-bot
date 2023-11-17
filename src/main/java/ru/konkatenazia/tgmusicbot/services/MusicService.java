package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {

    private final FileOperationService fileOperationService;

    @Async("saveMusicAsync")
    public CompletableFuture<Void> saveMusic(MultipartFile[] multipartFiles) {
        var filePaths = fileOperationService.saveArchivesAndGetAbsPaths(multipartFiles);
        var extractedFilesPath = fileOperationService.extractArchivesAndGetAbsPaths(filePaths);
        return CompletableFuture.completedFuture(null)
                .thenRun(() -> log.info("Cохранение музыки асинхронно завершено!"))
                .thenRun(() -> extractMusicDataToDb(extractedFilesPath));
    }

    public void extractMusicDataToDb(List<String> musicPaths) {
        musicPaths.forEach(System.out::println);
    }
}