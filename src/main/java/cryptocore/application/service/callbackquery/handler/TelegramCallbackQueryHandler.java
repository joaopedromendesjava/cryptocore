package cryptocore.application.service.callbackquery.handler;

import cryptocore.application.model.UpdateChat;
import cryptocore.application.model.enums.TelegramCallbackQueryEnum;
import cryptocore.application.model.enums.TelegramCommand;

public interface TelegramCallbackQueryHandler {
    boolean supports(TelegramCallbackQueryEnum callbackQueryEnum);
    void handle(UpdateChat update);
}
