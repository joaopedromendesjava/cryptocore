package cryptocore.infrastructure.input.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record BinanceTickerResponse(
        @JsonProperty("e")
        String eventType,
        @JsonProperty("E")
        Long eventTime,
        @JsonProperty("s")
        String symbol,
        @JsonProperty("c")
        BigDecimal lastPrice,
        @JsonProperty("Q")
        BigDecimal lastQuantity,
        @JsonProperty("b")
        BigDecimal bestBidPrice,
        @JsonProperty("B")
        BigDecimal bestBidQty,
        @JsonProperty("a")
        BigDecimal bestAskPrice,
        @JsonProperty("A")
        BigDecimal bestAskQty,
        @JsonProperty("o")
        BigDecimal openPrice,
        @JsonProperty("h")
        BigDecimal highPrice,
        @JsonProperty("l")
        BigDecimal lowPrice,
        @JsonProperty("p")
        BigDecimal priceChange) { }
