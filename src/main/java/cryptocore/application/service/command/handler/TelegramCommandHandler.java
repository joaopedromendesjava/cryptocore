package cryptocore.application.service.command.handler;

import cryptocore.application.model.UpdateChat;
import cryptocore.application.model.enums.TelegramCommand;

public interface TelegramCommandHandler {
    boolean supports(TelegramCommand command);
    void handle(UpdateChat update);
}
