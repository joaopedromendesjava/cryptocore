package cryptocore.application.service.command.factory;

import cryptocore.application.model.enums.TelegramCommand;
import cryptocore.application.service.command.handler.TelegramCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramCommandFactory {

    private final List<TelegramCommandHandler> handlers;

    public TelegramCommandHandler resolve(String text){
        return handlers.stream()
                .filter(handler -> handler.supports(TelegramCommand.from(text)))
                .findFirst()
                .orElseThrow();
    }
}
