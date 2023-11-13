package ru.konkatenazia.tgmusicbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "swear_word")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwearWord {

    @Id
    private Long id;

    private String word;

}
