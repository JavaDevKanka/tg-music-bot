package ru.konkatenazia.tgmusicbot.services.fileoperation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.konkatenazia.tgmusicbot.exception.SavingFileException;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileOperationService {

    private final BotHeart botHeart;
    private static final String MUSIC_ARCHIVE_PATH = "music-files/archive";
    private static final String UNPACKED_MUSIC_PATH = "music-files/music";
    private static final String FILE_EXTENSION_7Z = ".7z";

    public String extractMp3FromArchiveAndGetPathAndNameOfFile(String archiveName) {
        try (SevenZFile sevenZFile = new SevenZFile(new File(MUSIC_ARCHIVE_PATH + "/" + archiveName))) {
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
                }
                return outputFile.getAbsolutePath();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Не удалось распаковать и сохранить Audio : " + e);
        }
        return "";
    }

    public String saveArchiveFileAndGetFileName(Message message) {
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
                    Path savePath = Path.of(MUSIC_ARCHIVE_PATH, fileName);
                    Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);

                    log.info("Archive file with name " + fileName + " saved successfully into : " + savePath);
                    return fileName;
                } catch (TelegramApiException | IOException e) {
                    throw new SavingFileException("Error saving file " + e.getMessage());
                }
            } else {
                throw new SavingFileException("Message does not contain an archive file. Throw from musicService.saveArchiveFile");
            }
        } else {
            throw new SavingFileException("Message does not contain a document. Throw from musicService.saveArchiveFile");
        }
    }
}
