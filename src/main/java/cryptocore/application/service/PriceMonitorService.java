package cryptocore.application.service;

import cryptocore.application.model.AnalysisResult;
import cryptocore.application.model.PriceHistory;
import cryptocore.application.model.TickerData;
import cryptocore.application.model.enums.Signal;
import cryptocore.application.port.NotificationPort;
import cryptocore.application.port.PriceUpdatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceMonitorService implements PriceUpdatePort {

    private final PriceHistory priceHistory;
    private final PriceAnalysisService priceAnalysisService;
    private final NotificationPort notificationPort;
    private final AlertMessageService alertMessageService;
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
        String message = alertMessageService.build(analysis);
        processAlert(Instant.now(), analysis, message);
    }

    private void processAlert(Instant now, AnalysisResult analysis, String message) {
        if (lastAlertTime == null || Duration.between(lastAlertTime, now).toMinutes() >= 5) {

            boolean signalChanged = (lastSignalSent == null || analysis.signal() != lastSignalSent);

            if (signalChanged) {
                notificationPort.sendPriceAlert(message);
                lastSignalSent = analysis.signal();
                lastAlertTime = now;

            } else if (analysis.signal() == Signal.WAIT) {
                notificationPort.sendPriceAlert(message);
                lastAlertTime = now;
            }
        }
    }

}
