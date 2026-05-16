package cryptocore.application.service;

import cryptocore.application.model.AnalysisResult;
import cryptocore.application.model.PriceHistory;
import cryptocore.application.model.TickerData;
import cryptocore.application.model.enums.Signal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class PriceAnalysisService {

    public AnalysisResult analyze(TickerData tickerData, PriceHistory priceHistory) {
        var priceChange24h = tickerData.priceChangePercent();
        var shortTermChange = priceHistory.getShortTermChange();

        var sma = priceHistory.getAverage();
        var priceVsSma = tickerData.lastPrice().subtract(sma);

        var volatilityResult = calculateVolatility(tickerData);

        int score = calculateScore(priceChange24h, shortTermChange, priceVsSma, volatilityResult);

        var signal = determineSignal(score);
        return AnalysisResult.builder()
                .sma(sma)
                .priceChange24h(priceChange24h)
                .priceVsSma(priceVsSma)
                .symbol(tickerData.symbol())
                .lastPrice(tickerData.lastPrice())
                .shortTermChange(shortTermChange)
                .volatility(volatilityResult)
                .signal(signal)
                .score(score).build();
    }

    private Signal determineSignal(int score) {
        if (score >= 50) {
            return Signal.BUY;
        }
        if (score <= -50) {
            return Signal.SELL;
        }
        return Signal.WAIT;
    }

    private BigDecimal calculateVolatility(TickerData tickerData) {
        var diff = tickerData.highPrice().subtract(tickerData.lowPrice());

        var division = diff.divide(tickerData.lastPrice(), 8, RoundingMode.HALF_EVEN);

        return division.multiply(new BigDecimal("100")).setScale(4, RoundingMode.HALF_EVEN);
    }

    private int calculateScore(BigDecimal priceChange24h, BigDecimal shortTermChange, BigDecimal priceVsSma, BigDecimal volatility) {
        int score = 0;

        score += evaluate24hChange(priceChange24h);
        score += evaluateShortTermMomentum(shortTermChange);
        score += evaluateTrendAlignment(priceVsSma);
        score += evaluateVolatilityRisk(volatility);
        return score;
    }

    private int evaluateVolatilityRisk(BigDecimal volatility) {
        if (volatility.compareTo(new BigDecimal("8")) > 0) {
            return -30;
        }
        if (volatility.compareTo(new BigDecimal("3")) < 0) {
            return 15;
        }
        return 0;
    }

    private int evaluateTrendAlignment(BigDecimal priceVsSma) {
        if (priceVsSma.compareTo(BigDecimal.ZERO) > 0) {
            return 20;
        }
        if (priceVsSma.compareTo(BigDecimal.ZERO) < 0) {
            return -20;
        }
        return 0;
    }

    private int evaluateShortTermMomentum(BigDecimal shortTermChange) {
        if (shortTermChange.compareTo(new BigDecimal("0.5")) > 0){
            return 30;
        }
        if (shortTermChange.compareTo(new BigDecimal("-0.5")) < 0){
            return -30;
        }
        return 0;
    }

    private int evaluate24hChange(BigDecimal priceChange24h) {
        if (priceChange24h.compareTo(new BigDecimal("-3")) < 0) {
            return 25;
        }
        if (priceChange24h.compareTo(new BigDecimal("5")) > 0) {
            return -20;
        }
        return 0;
    }

}
