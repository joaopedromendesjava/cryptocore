package cryptocore.infrastructure.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramRequestDTO(
        @JsonProperty("chat_id")
        String chatId,
        String text,
        @JsonProperty("parse_mode")
        String parseMode) {
}
