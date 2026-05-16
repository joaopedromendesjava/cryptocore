package cryptocore.application.model;

import cryptocore.application.model.enums.Signal;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AnalysisResult(
        String symbol,
        BigDecimal lastPrice,
        BigDecimal priceChange24h,
        BigDecimal shortTermChange,
        BigDecimal volatility,
        BigDecimal sma,
        BigDecimal priceVsSma,
        int score,
        Signal signal) {
}
