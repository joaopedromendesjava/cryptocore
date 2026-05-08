package cryptocore.infrastructure.input.websocket.mapper;

import cryptocore.application.model.TickerData;
import cryptocore.infrastructure.input.websocket.dto.BinanceTickerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface WebSocketTickerMapper {

    @Mapping(source = "eventTime", target = "eventTime", qualifiedByName = "mapToLocalDate")
    TickerData toModel(BinanceTickerResponse response);

    @Named("mapToLocalDate")
    default LocalDateTime mapLongToLocalDate(Long eventTime){
        return Instant.ofEpochMilli(eventTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
