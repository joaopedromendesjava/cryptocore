package cryptocore.application.mapper;

import cryptocore.application.model.UpdateChat;
import cryptocore.infrastructure.output.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "telegramId", source = "message.from.id")
    @Mapping(target = "firstName", source = "message.from.firstName")
    @Mapping(target = "userName", source = "message.from.userName")
    @Mapping(target = "languageCode", source = "message.from.languageCode")
    User toEntity(UpdateChat update);
}
