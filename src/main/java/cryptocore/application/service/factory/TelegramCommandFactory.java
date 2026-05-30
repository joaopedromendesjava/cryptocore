package cryptocore.application.service.factory;

import cryptocore.application.model.enums.TelegramCommand;
import cryptocore.application.service.handler.TelegramCommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelegramCommandFactory {

    private final List<TelegramCommandHandler> handlers;

    public TelegramCommandFactory(List<TelegramCommandHandler> handlers) {
        this.handlers = handlers;
    }

    public TelegramCommandHandler resolve(String text){
        return handlers.stream()
                .filter(handler -> handler.supports(TelegramCommand.from(text)))
                .findFirst()
                .orElseThrow();
    }
}
