package cryptocore.application.service;

import cryptocore.application.model.AnalysisResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AlertMessageService {

    private static final int SCALE = 4;

    public String build(AnalysisResult analysis) {
        return switch (analysis.signal()) {
            case BUY  -> buildBuyAlert(analysis);
            case SELL -> buildSellAlert(analysis);
            case WAIT -> buildResume(analysis);
        };
    }

    private String buildBuyAlert(AnalysisResult a) {
        return String.format(
                "🟢 <b>COMPRA</b> — %s%n" +
                        "💰 Preço: $%s%n" +
                        "📊 Score: %d%n" +
                        "📉 24h: %s%% | ⏱️ 5min: %s%%%n" +
                        "📏 Vol: %s%% | 🔁 vs SMA: %s",
                a.symbol(),
                format(a.lastPrice()),
                a.score(),
                formatPercent(a.priceChange24h()),
                formatPercent(a.shortTermChange()),
                formatPercent(a.volatility()),
                format(a.priceVsSma())
        );
    }

    private String buildSellAlert(AnalysisResult a) {
        return String.format(
                "🔴 <b>VENDA</b> — %s%n" +
                        "💰 Preço: $%s%n" +
                        "📊 Score: %d%n" +
                        "📈 24h: %s%% | ⏱️ 5min: %s%%%n" +
                        "📏 Vol: %s%% | 🔁 vs SMA: %s",
                a.symbol(),
                format(a.lastPrice()),
                a.score(),
                formatPercent(a.priceChange24h()),
                formatPercent(a.shortTermChange()),
                formatPercent(a.volatility()),
                format(a.priceVsSma())
        );
    }

    private String buildResume(AnalysisResult a) {
        return String.format(
                "🔵 <b>RESUMO</b> — %s%n" +
                        "💰 Preço: $%s%n" +
                        "📊 Score: %d | Sinal: %s%n" +
                        "📈 24h: %s%% | ⏱️ 5min: %s%%%n" +
                        "📏 Vol: %s%% | SMA: $%s",
                a.symbol(),
                format(a.lastPrice()),
                a.score(),
                a.signal(),
                formatPercent(a.priceChange24h()),
                formatPercent(a.shortTermChange()),
                formatPercent(a.volatility()),
                format(a.sma())
        );
    }

    private String format(BigDecimal value) {
        return value != null ? value.setScale(SCALE, RoundingMode.HALF_EVEN).toPlainString() : "N/A";
    }

    private String formatPercent(BigDecimal value) {
        return value != null ? value.setScale(SCALE, RoundingMode.HALF_EVEN).toPlainString() : "N/A";
    }
}
