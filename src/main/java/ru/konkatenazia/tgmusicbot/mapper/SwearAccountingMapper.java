package ru.konkatenazia.tgmusicbot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.konkatenazia.tgmusicbot.model.ChatUser;
import ru.konkatenazia.tgmusicbot.model.SwearAccounting;

@Mapper(componentModel = "spring",
        imports = {String.class})
public abstract class SwearAccountingMapper {

    @Mapping(target = "chatUser", source = "chatUser")
    @Mapping(target = "swearWord", source = "message")
    public abstract SwearAccounting toSwearAccounting(ChatUser chatUser, String message);
}
