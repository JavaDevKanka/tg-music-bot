package ru.konkatenazia.tgmusicbot.services;


import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konkatenazia.tgmusicbot.repository.MusicRepository;
import ru.konkatenazia.tgmusicbot.repository.SongRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public record MusicService(
        BotHeart botHeart,
        MusicRepository musicRepository,
        SongRepository songRepository
) {

    private static final String MUSIC_ARCHIVE_PATH = "music-files/archive";
    private static final String UNPACKED_MUSIC_PATH = "music-files/music";
    public void unpackAndSaveMusic(Message message) {
        saveArchiveFile(message, MUSIC_ARCHIVE_PATH);
//        extractArchive();
        botHeart.sendMessage(message.getChatId(), "Файлы распакованы и сохранены");
    }


    public void saveArchiveFile(Message message, String saveDirectory) {
        if (message.hasDocument()) {
            Document document = message.getDocument();
            if (document.getMimeType().equals("application/x-7z-compressed")) {
                String fileId = document.getFileId();
                String fileName = document.getFileName();
                try {
                    GetFile getFile = new GetFile();
                    getFile.setFileId(fileId);
                    org.telegram.telegrambots.meta.api.objects.File file = botHeart.execute(getFile);

                    InputStream inputStream = botHeart.downloadFileAsStream(file.getFilePath());
                    Path savePath = Path.of(saveDirectory, fileName);
                    Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Archive file saved successfully: " + savePath);
                } catch (TelegramApiException | IOException e) {
                    System.out.println("Error saving archive file: " + e.getMessage());
                }
            } else {
                System.out.println("Message does not contain an archive file.");
            }
        } else {
            System.out.println("Message does not contain a document.");
        }
    }

    public void extractArchive() {
        try (FileInputStream fis = new FileInputStream(MUSIC_ARCHIVE_PATH);
             ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream(fis)) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                if (!ais.canReadEntryData(entry)) {
                    continue;
                }

                String entryName = entry.getName();
                File outputFile = new File(UNPACKED_MUSIC_PATH, entryName);

                if (entry.isDirectory()) {
                    if (!outputFile.exists()) {
                        var outputFileVar = outputFile.mkdirs();
                    }
                } else {
                    File parentDir = outputFile.getParentFile();
                    if (!parentDir.exists()) {
                        var parentDirVar= parentDir.mkdirs();
                    }

                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = ais.read(buffer)) != -1) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
            }
        } catch (IOException | ArchiveException e) {
            throw new RuntimeException("Не удалось распаковать и сохранить Audio : " + e);
        }
    }


}