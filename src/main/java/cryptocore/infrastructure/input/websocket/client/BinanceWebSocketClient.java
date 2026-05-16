package cryptocore.infrastructure.input.websocket.client;

import cryptocore.infrastructure.input.websocket.handler.impl.BinanceTradeHandler;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BinanceWebSocketClient extends WebSocketListener {

    private WebSocket webSocket;
    private final BinanceTradeHandler handler;
    private final OkHttpClient client;

    public void connect() {

        Request request = new Request.Builder()
                .url("wss://stream.binance.com:9443/ws/dogeusdt@ticker")
                .build();

        this.webSocket = client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        handler.onOpen();
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        handler.onClosing(code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        handler.onError(t);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        handler.onMessage(text);
    }
}
