package cryptocore.application.port;

import cryptocore.application.model.TickerData;

public interface PriceUpdatePort {
    void onPriceUpdate(TickerData tickerData);
}
