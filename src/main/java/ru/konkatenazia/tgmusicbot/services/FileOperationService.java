package ru.konkatenazia.tgmusicbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileOperationService {
    @Value("${file.archive.path}")
    private String MUSIC_ARCHIVE_PATH;
    @Value("${file.music.path}")
    private String UNPACKED_MUSIC_PATH;

    public List<String> saveArchivesAndGetAbsPaths(MultipartFile[] musicArchives) {
        List<String> result = new ArrayList<>();
        for (MultipartFile file : musicArchives) {
            var pathToSavedFile = saveArchiveAndGetAbsPath(file);
            if (pathToSavedFile == null) {
                continue;
            }
            result.add(pathToSavedFile);
        }
        return result;
    }

    public String saveArchiveAndGetAbsPath(MultipartFile musicArchive) {
        try {
            byte[] bytes = musicArchive.getBytes();
            File outputFile = new File(MUSIC_ARCHIVE_PATH, Objects.requireNonNull(musicArchive.getOriginalFilename()));
            if (!outputFile.exists()) {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                stream.write(bytes);
                stream.close();
                return outputFile.getAbsolutePath();
            }
            log.warn("Файл с именем {} уже сохранен", outputFile.getName());
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Не удалось сохранить файл " + musicArchive.getName());
        }
    }

    public List<String> extractArchivesAndGetAbsPaths(List<String> pathsToArchive) {
        return pathsToArchive.stream()
                .flatMap(pathToArchive -> extractArchiveAndGetAbsPath(pathToArchive).stream())
                .toList();
    }

    public List<String> extractArchiveAndGetAbsPath(String archiveName) {
        List<String> unpackedSongsAbsPaths = new ArrayList<>();
        if (!archiveName.endsWith(".7z")) {
            return null;
        }
        try (SevenZFile sevenZFile = new SevenZFile(new File(archiveName))) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                String entryName = entry.getName();
                File outputFile = new File(UNPACKED_MUSIC_PATH, entryName);

                File parentDir = outputFile.getParentFile();
                if (!parentDir.exists()) {
                    var parentDirMk = parentDir.mkdirs();
                }

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = sevenZFile.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    return null;
                }
                unpackedSongsAbsPaths.add(outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return unpackedSongsAbsPaths;
    }

}
