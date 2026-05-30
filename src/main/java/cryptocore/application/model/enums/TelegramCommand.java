package cryptocore.application.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TelegramCommand {
    START("/start");

    private final String value;

    public static TelegramCommand from(String text){
        return Arrays.stream(values())
                .filter(command -> command.value.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid Command"));
    }
}
