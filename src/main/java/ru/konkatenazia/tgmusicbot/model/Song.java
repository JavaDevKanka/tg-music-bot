package ru.konkatenazia.tgmusicbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.hibernate.annotations.CreationTimestamp;
import ru.konkatenazia.tgmusicbot.dto.enums.Style;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "song")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    private UUID id;

    @CreationTimestamp
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private Style style;

    private TimeStamp releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id", referencedColumnName = "id",nullable = false)
    private Music music;
}