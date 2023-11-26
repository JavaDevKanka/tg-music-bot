package ru.konkatenazia.tgmusicbot.dto.word;

import lombok.Data;

@Data
public class PhoneticDTO {
    private String text;
    private String audio;
    private String sourceUrl;
    private LicenseDTO license;
}
