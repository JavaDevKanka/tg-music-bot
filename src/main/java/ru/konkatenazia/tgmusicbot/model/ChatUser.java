package ru.konkatenazia.tgmusicbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "user_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {

    @Id
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    private LocalDateTime created;
}
