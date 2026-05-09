package cryptocore.application.service;

import cryptocore.application.model.TickerData;
import cryptocore.application.port.PriceUpdatePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceMonitorService implements PriceUpdatePort {

    @Override
    public void onPriceUpdate(TickerData tickerData) {

        log.info("{} - Preço: {} | Variação: {}%",
                tickerData.symbol(), tickerData.lastPrice(), tickerData.priceChangePercent());
    }
}
