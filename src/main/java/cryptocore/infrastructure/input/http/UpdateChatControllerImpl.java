package cryptocore.infrastructure.input.http;

import cryptocore.application.service.factory.TelegramCommandFactory;
import cryptocore.infrastructure.input.mapper.UpdateChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.api.WebhookApi;
import org.openapitools.model.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UpdateChatControllerImpl implements WebhookApi {

    private final TelegramCommandFactory telegramCommandFactory;
    private final UpdateChatMapper updateChatMapper;

    @Value("${secret.token}")
    private String secret;

    @Override
    public ResponseEntity<Void> receiveTelegramUpdate(Update update, String xTelegramBotApiSecretToken) {
        if (StringUtils.isBlank(xTelegramBotApiSecretToken) && !xTelegramBotApiSecretToken.equals(secret)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var updated = updateChatMapper.toUpdateMessage(update);
        var text = updated.message().text().trim();

        var commandHandler = telegramCommandFactory.resolve(text);
        commandHandler.handle(updated);

        log.info("Request received and user created for chatId {}", updated.message().chat().id());
        return ResponseEntity.ok().build();
    }
}
