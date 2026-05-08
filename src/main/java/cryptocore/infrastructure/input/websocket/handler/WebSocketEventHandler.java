package cryptocore.infrastructure.input.websocket.handler;

public interface WebSocketEventHandler {

    void onOpen();

    void onMessage(String message);

    void onClosing(int code, String reason);

    void onError(Throwable throwable);
}
