package cryptocore.application.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TickerData(String eventType,
                         LocalDateTime eventTime,
                         String symbol,
                         BigDecimal lastPrice,
                         BigDecimal lastQuantity,
                         BigDecimal bestBidPrice,
                         BigDecimal bestBidQty,
                         BigDecimal bestAskPrice,
                         BigDecimal bestAskQty,
                         BigDecimal openPrice,
                         BigDecimal highPrice,
                         BigDecimal lowPrice,
                         BigDecimal priceChange) {
}
