package cryptocore.application.model;

import java.math.BigDecimal;
import java.time.Instant;

public record PriceEntry(BigDecimal lastPrice, Instant timestamp) {
}
