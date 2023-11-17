package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {

    private final FileOperationService fileOperationService;

    public void saveMusic(MultipartFile[] multipartFiles) {
        var filePaths = fileOperationService.saveArchivesAndGetAbsPaths(multipartFiles);
        var extractedFilesPath = fileOperationService.extractArchivesAndGetAbsPaths(filePaths);
        extractedFilesPath.forEach(System.out::println);
    }
}