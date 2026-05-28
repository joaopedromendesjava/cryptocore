package cryptocore.infrastructure.input.http;

import cryptocore.application.port.UpdateMessageRequestPort;
import cryptocore.infrastructure.input.mapper.UpdateChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.api.WebhookApi;
import org.openapitools.model.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UpdateChatControllerImpl implements WebhookApi {

    private final UpdateMessageRequestPort messageRequestPort;
    private final UpdateChatMapper updateChatMapper;

    @Override
    public ResponseEntity<Void> receiveTelegramUpdate(Update update, String xTelegramBotApiSecretToken) {
        if (StringUtils.isBlank(xTelegramBotApiSecretToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var updated = updateChatMapper.toUpdateMessage(update);
        messageRequestPort.updatedMessage(updated);

        log.info("Request received and user created for chatId {}", updated.message().chat().id());
        return ResponseEntity.ok().build();
    }
}
