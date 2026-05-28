package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record From(
        Long id,
        @JsonProperty("is_bot")
        boolean isBot,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("username")
        String userName,
        @JsonProperty("language_code")
        String languageCode) { }
