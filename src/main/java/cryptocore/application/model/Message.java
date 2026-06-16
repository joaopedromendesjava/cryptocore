package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(
        @JsonProperty("message_id") Long messageId,
        From from,
        Chat chat,
        String text,
        OffsetDateTime date,
        List<EntityChat> entities) {
}
