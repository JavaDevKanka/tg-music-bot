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

    public void removeUnpackedArchives(List<String> pathsToArchive) {
        pathsToArchive.forEach(this::removeUnpackedArchive);
    }

    public void removeUnpackedArchive(String pathToArchive) {
        File file = new File(pathToArchive);
        var fileName = file.getName();
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                log.info("Архив \"{}\" был удален после распаковки.", fileName);
            } else {
                log.info("Архив \"{}\" удалить не удалось.", fileName);
            }
        } else {
            log.info("Файл \"{}\" не существует", fileName);
        }
    }

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

            File musicArchiveDir = new File(MUSIC_ARCHIVE_PATH);
            if (!musicArchiveDir.exists()) {
                boolean created = musicArchiveDir.mkdirs();
                if (!created) {
                    throw new RuntimeException("Не удалось создать директорию для архивов музыки");
                }
            }

            File outputFile = new File(MUSIC_ARCHIVE_PATH, Objects.requireNonNull(musicArchive.getOriginalFilename()));
            if (!outputFile.exists()) {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outputFile));
                stream.write(bytes);
                stream.close();
                return outputFile.getAbsolutePath();
            }
            log.warn("Файл с именем \"{}\" уже сохранен", outputFile.getName());
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Не удалось сохранить файл \"" + musicArchive.getName() + "\"");
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
            log.info("Файл с именем \"" + archiveName + "\" не является архивом *.7z");
        }
        try (SevenZFile sevenZFile = new SevenZFile(new File(archiveName))) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                String entryName = entry.getName();
                if (entryName.endsWith(".flac") || entryName.endsWith(".mp3")) {
                    log.info("Распаковывается файл \"{}\"", entryName);

                    File unpackedMusicDir = new File(UNPACKED_MUSIC_PATH);
                    if (!unpackedMusicDir.exists()) {
                        boolean created = unpackedMusicDir.mkdirs();
                        if (!created) {
                            throw new RuntimeException("Не удалось создать директорию для распакованной музыки");
                        }
                    }

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
            }
        } catch (IOException e) {
            log.error("Не удалось распаковать архив \"{}\"", archiveName);
            throw new RuntimeException(e.getMessage());
        }
        return unpackedSongsAbsPaths;
    }

}
