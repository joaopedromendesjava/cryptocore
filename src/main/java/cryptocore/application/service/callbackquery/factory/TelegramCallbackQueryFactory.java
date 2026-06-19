package cryptocore.application.service.callbackquery.factory;

import cryptocore.application.model.enums.TelegramCallbackQueryEnum;
import cryptocore.application.service.callbackquery.handler.TelegramCallbackQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramCallbackQueryFactory {

    private final List<TelegramCallbackQueryHandler> callbackQueryHandlers;

    public TelegramCallbackQueryHandler resolve(String callback) {
        return callbackQueryHandlers.stream()
                .filter(handler -> handler.supports(TelegramCallbackQueryEnum.from(callback)))
                .findFirst()
                .orElseThrow();
    }
}
