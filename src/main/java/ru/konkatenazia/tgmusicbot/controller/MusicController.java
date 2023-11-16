package ru.konkatenazia.tgmusicbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.konkatenazia.tgmusicbot.services.MusicService;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController("/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @PostMapping(value = "/getFileList", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> getFileList(@RequestPart("musicArchive") MultipartFile[] musicArchive) {
        if (musicArchive.length >= 1) {
            musicService.unpackMusic(musicArchive);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
