package ru.konkatenazia.tgmusicbot.dto.enums.word;

import lombok.Data;

import java.util.List;

@Data
public class DefinitionDTO {
    private String definition;
    private List<String> synonyms;
    private List<String> antonyms;
    private String example;
}