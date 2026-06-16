package cryptocore.application.boundary;

import cryptocore.infrastructure.output.dto.TelegramRequestDTO;

public interface TelegramClient {
    void sendMessage(TelegramRequestDTO telegramRequestDTO);
}
