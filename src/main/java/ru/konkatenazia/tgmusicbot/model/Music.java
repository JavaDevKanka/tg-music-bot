package ru.konkatenazia.tgmusicbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.konkatenazia.tgmusicbot.dto.enums.Country;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "music")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    @Id
    private UUID id;

    private String songName;

    private String author;

    @Enumerated(EnumType.STRING)
    private Country country;

    @ManyToOne(cascade = CascadeType.ALL)
    private Song song;
}