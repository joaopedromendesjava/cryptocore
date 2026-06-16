package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UpdateChat(
        @JsonProperty("update_id") Long updateId,
        Message message,
        @JsonProperty("callback_query")
        CallbackQuery callbackQuery) {
}
