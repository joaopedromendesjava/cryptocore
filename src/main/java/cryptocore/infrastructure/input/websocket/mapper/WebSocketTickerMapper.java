package cryptocore.infrastructure.input.websocket.mapper;

import cryptocore.application.model.TickerData;
import cryptocore.infrastructure.input.websocket.dto.BinanceTickerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface WebSocketTickerMapper {

    @Mapping(source = "eventTime", target = "eventTime", qualifiedByName = "mapToInstant")
    TickerData toModel(BinanceTickerResponse response);

    @Named("mapToInstant")
    default Instant mapLongToLocalDate(Long eventTime){
        return Instant.ofEpochMilli(eventTime)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
