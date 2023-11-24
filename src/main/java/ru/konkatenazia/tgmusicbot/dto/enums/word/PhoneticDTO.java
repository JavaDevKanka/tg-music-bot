package ru.konkatenazia.tgmusicbot.dto.enums.word;

import lombok.Data;

@Data
public class PhoneticDTO {
    private String text;
    private String audio;
    private String sourceUrl;
    private LicenseDTO license;
}
