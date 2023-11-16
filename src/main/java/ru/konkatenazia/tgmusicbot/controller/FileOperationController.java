package ru.konkatenazia.tgmusicbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/music")
@RequiredArgsConstructor
public class FileOperationController {

    @GetMapping("/getFileList")
    public ResponseEntity<List> getFileList() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
