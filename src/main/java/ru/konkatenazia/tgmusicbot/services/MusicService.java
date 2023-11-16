package ru.konkatenazia.tgmusicbot.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.konkatenazia.tgmusicbot.repository.MusicRepository;
import ru.konkatenazia.tgmusicbot.repository.SongRepository;
import ru.konkatenazia.tgmusicbot.services.basebot.BotHeart;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicService {

    private final BotHeart botHeart;
    private final MusicRepository musicRepository;
    private final SongRepository songRepository;

}