package cryptocore.infrastructure.output.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TelegramRequestDTO(
        @JsonProperty("chat_id")
        Long chatId,
        String text,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("reply_markup")
        String replyMarkup,
        @JsonProperty("parse_mode")
        String parseMode) {
}
