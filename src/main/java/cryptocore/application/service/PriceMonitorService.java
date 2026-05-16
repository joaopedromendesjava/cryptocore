package cryptocore.application.service;

import cryptocore.application.model.PriceHistory;
import cryptocore.application.model.TickerData;
import cryptocore.application.model.enums.Signal;
import cryptocore.application.port.PriceUpdatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceMonitorService implements PriceUpdatePort {

    private final PriceHistory priceHistory;
    private final PriceAnalysisService priceAnalysisService;
    private Signal lastSignalSent = null;
    private Instant lastAlertTime = null;

    @Override
    public void onPriceUpdate(TickerData tickerData) {

        priceHistory.add(tickerData.lastPrice(), tickerData.eventTime());

        var analysis = priceAnalysisService.analyze(tickerData, priceHistory);
        log.info("[{}] Preço: {} | 24h: {}% | 5min: {}% | Vol: {}% | SMA: {} | Score: {} | Sinal: {} | Ticks: {}",
                analysis.symbol(),
                analysis.lastPrice(),
                analysis.priceChange24h(),
                analysis.shortTermChange(),
                analysis.volatility(),
                analysis.sma(),
                analysis.score(),
                analysis.signal(),
                priceHistory.size()
        );
        if (lastAlertTime == null || lastAlertTime.plus(5, ChronoUnit.MINUTES).isAfter(Instant.now())){
            if(analysis.signal() != lastSignalSent){
               // alertamudanca
            }
           // alertaperiodico
        }
        lastSignalSent = analysis.signal();
        lastAlertTime = Instant.now();
    }

}
