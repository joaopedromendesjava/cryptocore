package cryptocore.infrastructure.input.mapper;

import cryptocore.application.model.UpdateChat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.Update;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface UpdateChatMapper {

    @Mapping(target = "message.date", source = "message.date", qualifiedByName = "mapToOffSetDate")
    @Mapping(target = "callbackQuery.message.date", source = "callbackQuery.message.date", qualifiedByName = "mapToOffSetDate")
    UpdateChat toUpdateMessage(Update update);

    @Named("mapToOffSetDate")
    default OffsetDateTime toOffSetDate(Integer date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochSecond(date)
                .atZone(ZoneId.of("UTC"))
                .toOffsetDateTime();
    }
}
