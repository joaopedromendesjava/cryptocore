package cryptocore.application.service.callbackquery.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import cryptocore.AppConstants;
import cryptocore.application.model.UpdateChat;
import cryptocore.application.model.enums.TelegramCallbackQueryEnum;
import cryptocore.application.port.UpdateMessageRequestPort;
import cryptocore.application.service.callbackquery.handler.TelegramCallbackQueryHandler;
import cryptocore.infrastructure.output.dto.TelegramRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateAlertCallbackHandler implements TelegramCallbackQueryHandler {

    private final ObjectMapper mapper;
    private final UpdateMessageRequestPort updateMessageRequestPort;

    @Override
    public boolean supports(TelegramCallbackQueryEnum callbackQueryEnum) {
        return TelegramCallbackQueryEnum.CREATE_ALERT.equals(callbackQueryEnum);
    }

    @Override
    public void handle(UpdateChat update) {
        var chatId = update.callbackQuery().message().chat().id();
        try {
            log.info("Received create alert");

            buildSymbolSelectionMessage(chatId);
            updateMessageRequestPort.updatedMessage(buildSymbolSelectionMessage(chatId));

        } catch (Exception e) {
            log.error("Erro ao processar o callback CREATE_ALERT para o chat {}", chatId, e);
            throw new RuntimeException("Falha ao processar callback CREATE_ALERT", e);
        }
    }

    private TelegramRequestDTO buildSymbolSelectionMessage(Long chatId) throws Exception {
        List<List<Map<String, String>>> inlineKeyboard = new ArrayList<>();

        for (String symbol : AppConstants.SupportedSymbol.symbols) {
            Map<String, String> button = Map.of(
                    "text", symbol,
                    "callback_data", "COIN:" + symbol
            );
            inlineKeyboard.add(List.of(button));
        }
        var keyboard = Map.of("inline_keyboard", inlineKeyboard);
        String keyboardAsText = mapper.writeValueAsString(keyboard);

        return TelegramRequestDTO.builder()
                .chatId(chatId)
                .text("📈 Novo alerta \n\n Selecione o ativo que deseja monitorar:")
                .replyMarkup(keyboardAsText)
                .parseMode("HTML")
                .build();
    }
}
