package cryptocore.infrastructure.input.websocket.handler.impl;

import cryptocore.infrastructure.input.websocket.dto.BinanceTickerResponse;
import cryptocore.infrastructure.input.websocket.handler.WebSocketEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class BinanceTradeHandler implements WebSocketEventHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onOpen() {
        log.info("Connection successfully");
    }

    @Override
    public void onMessage(String message) {
        try {
            var binanceResponse = objectMapper.readValue(message, BinanceTickerResponse.class);

        } catch (Exception e) {
            log.error("Error processing message ", e);
        }

        log.info("Message {}", message);
    }

    @Override
    public void onClosing(int code, String reason) {
        log.warn("Socket closed with code {} and reason {}", code, reason);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error in websocket", throwable);

    }
}
