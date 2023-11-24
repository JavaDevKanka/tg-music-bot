package ru.konkatenazia.tgmusicbot.dto.enums.word;

import lombok.Data;

import java.util.List;

@Data
public class MeaningDTO {
    private String partOfSpeech;
    private List<DefinitionDTO> definitions;
    private List<String> synonyms;
    private List<String> antonyms;
    private String example;
}