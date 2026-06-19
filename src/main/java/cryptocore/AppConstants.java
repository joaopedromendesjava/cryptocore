package cryptocore;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AppConstants {

    @UtilityClass
    public static class SupportedSymbol {
        public static final List<String> symbols = List.of(
                "BTCUSDT",
                "ETHUSDT",
                "SOLUSDT",
                "DOGEUSDT");
    }

}
