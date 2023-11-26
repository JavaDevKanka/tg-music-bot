package ru.konkatenazia.tgmusicbot.dto.word;

import lombok.Data;

import java.util.List;

@Data
public class WordDTO {
    private String word;
    private String phonetic;
    private List<PhoneticDTO> phonetics;
    private List<MeaningDTO> meanings;
    private LicenseDTO license;
    private List<String> sourceUrls;
}