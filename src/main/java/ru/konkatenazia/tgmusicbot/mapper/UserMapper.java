package ru.konkatenazia.tgmusicbot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.konkatenazia.tgmusicbot.model.ChatUser;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    public abstract ChatUser toChatUser(User user);
}
