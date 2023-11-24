package ru.konkatenazia.tgmusicbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "swear_accounting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwearAccounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String swearWord;

    private Long chatId;

    private Boolean isGroupChat;

    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_table_id", referencedColumnName = "id",nullable = false)
    private ChatUser chatUser;

    @CreationTimestamp
    private LocalDate created;
}
