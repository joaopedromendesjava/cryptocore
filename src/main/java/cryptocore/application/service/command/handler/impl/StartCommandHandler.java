package cryptocore.application.service.command.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import cryptocore.application.mapper.UserMapper;
import cryptocore.application.model.UpdateChat;
import cryptocore.application.model.enums.TelegramCommand;
import cryptocore.application.port.UpdateMessageRequestPort;
import cryptocore.application.port.UserServicePort;
import cryptocore.application.service.command.handler.TelegramCommandHandler;
import cryptocore.infrastructure.output.dto.TelegramRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommandHandler implements TelegramCommandHandler {

    private final UserServicePort userServicePort;
    private final UserMapper userMapper;
    private final UpdateMessageRequestPort messageRequestPort;
    private final ObjectMapper mapper;

    @Override
    public boolean supports(TelegramCommand command) {
        return TelegramCommand.START.equals(command);
    }

    @Override
    public void handle(UpdateChat update) {
        var entity = userMapper.toEntity(update);
        var chatId = update.message().chat().id();
        try {
            userServicePort.save(entity);

            var messageStart = buildStartMessage(chatId);
            messageRequestPort.updatedMessage(messageStart);

        } catch (Exception e) {
            log.error("Erro ao processar o comando START para o chat {}", chatId, e);
            throw new RuntimeException("Falha ao processar comando START", e);
        }
    }

    private TelegramRequestDTO buildStartMessage(Long chatId) throws Exception {
        var keyboard = Map.of(
                "inline_keyboard", List.of(
                        List.of(Map.of("text", "📈 Criar alerta", "callback_data", "CREATE_ALERT")),
                        List.of(Map.of("text", "📋 Meus alertas", "callback_data", "LIST_ALERTS")),
                        List.of(Map.of("text", "❓ Ajuda", "callback_data", "HELP"))));
        String keyboardAsText = mapper.writeValueAsString(keyboard);

        return TelegramRequestDTO.builder()
                .chatId(chatId)
                .text("🚀 Bem-vindo ao CryptoCore\n\nEscolha uma opção:")
                .replyMarkup(keyboardAsText)
                .parseMode("HTML")
                .build();
    }
}
