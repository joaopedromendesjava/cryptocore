package cryptocore.application.model;

import lombok.Builder;

@Builder
public record EntityChat(
        Integer offset,
        Integer length,
        String type) {
}
