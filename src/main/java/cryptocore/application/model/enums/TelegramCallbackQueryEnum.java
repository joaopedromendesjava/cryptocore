package cryptocore.application.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TelegramCallbackQueryEnum {

    CREATE_ALERT("CREATE_ALERT"),
    COIN("COIN");

    private final String value;

    public static TelegramCallbackQueryEnum from(String text) {
        return Arrays.stream(values())
                .filter(call -> call.value.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid CallbackQuery"));
    }
}
