package cryptocore.application.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

@Component
public final class PriceHistory {

    private final Deque<PriceEntry> priceEntries;
    private static final int WINDOW_MINUTES = 5;

    public PriceHistory (){
        this.priceEntries = new ArrayDeque<>();
    }

    public void add(BigDecimal lastPrice, Instant timestamp) {
        var priceEntry = new PriceEntry(lastPrice, timestamp);

        if (!priceEntries.isEmpty()) {
            removeExpired();
        }

        priceEntries.addLast(priceEntry);
    }

    public int size() {
        return priceEntries.size();
    }

    public Optional<BigDecimal> getOldest() {
       return Optional.ofNullable(priceEntries.peekFirst())
                .map(PriceEntry::lastPrice);
    }

    public Optional<BigDecimal> getNewest() {
        return Optional.ofNullable(priceEntries.peekLast())
                .map(PriceEntry::lastPrice);
    }

    public void clear() {
        priceEntries.clear();
    }

    public BigDecimal getAverage() {
        if (priceEntries.isEmpty()) {
            return null;
        }
        var soma = priceEntries.stream()
                .map(PriceEntry::lastPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return soma.divide(BigDecimal.valueOf(priceEntries.size()), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getShortTermChange() {
        if (priceEntries.isEmpty() || priceEntries.size() <= 1){
            return BigDecimal.ZERO;
        }
        var first = priceEntries.peekFirst().lastPrice();
        var last = priceEntries.peekLast().lastPrice();
        var diff = last.subtract(first);

        var division = diff.divide(first, 8, RoundingMode.HALF_EVEN);

        var percentage = division.multiply(new BigDecimal("100"));

        return percentage.setScale(4, RoundingMode.HALF_EVEN);
    }

    private void removeExpired() {
        while (!priceEntries.isEmpty() && validateWindow(priceEntries.peekFirst().timestamp())) {
            priceEntries.pollFirst();
        }
    }

    private boolean validateWindow(Instant timestampPrice) {
        var duration = Duration.between(timestampPrice, Instant.now());

        return duration.toMinutes() > WINDOW_MINUTES;
    }
}
