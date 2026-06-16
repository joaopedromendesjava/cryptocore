package cryptocore.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CallbackQuery (
        String id,
        From from,
        Message message,
        String data
) { }


