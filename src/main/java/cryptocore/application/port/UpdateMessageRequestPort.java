package cryptocore.application.port;

import cryptocore.infrastructure.output.dto.TelegramRequestDTO;

public interface UpdateMessageRequestPort {
    void updatedMessage(TelegramRequestDTO telegramRequestDTO);
}
