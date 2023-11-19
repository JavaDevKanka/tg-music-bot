package ru.konkatenazia.tgmusicbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.konkatenazia.tgmusicbot.services.async.MusicAsyncService;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController("/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicAsyncService musicAsyncService;

    @PostMapping(value = "/uploadMusic", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> uploadMusic(
            @RequestPart("musicArchive") MultipartFile[] musicArchive) {
        if (musicArchive.length >= 1) {
            musicAsyncService.saveMusicAsync(musicArchive);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}