package cryptocore.infrastructure.output.service.impl;

import cryptocore.application.port.UpdateMessageRequestPort;
import cryptocore.infrastructure.output.dto.TelegramRequestDTO;
import cryptocore.infrastructure.output.http.TelegramNotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMessageRequestServiceImpl implements UpdateMessageRequestPort {

    private final TelegramNotificationClient telegramNotificationClient;

    @Override
    public void updatedMessage(TelegramRequestDTO telegramRequestDTO) {
        telegramNotificationClient.sendToChat(telegramRequestDTO);
    }
}
