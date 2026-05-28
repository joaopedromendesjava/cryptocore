package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record Message(
        @JsonProperty("message_id") Long messageId,
        From from,
        Chat chat,
        String text,
        OffsetDateTime date,
        List<EntityChat> entities) {
}
