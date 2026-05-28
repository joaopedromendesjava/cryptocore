package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Chat(
        Long id,
        @JsonProperty("first_name") String firstName,
        String type) {
}
