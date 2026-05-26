package cryptocore.application.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeFrame {

    ONE_MINUTE("1m", 1),
    FIVE_MINUTES("5m", 5),
    FIFTEEN_MINUTES("15m", 15),
    ONE_HOUR("1h", 60);

    private final String binanceValue;
    private final int minutes;

}
