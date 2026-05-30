package cryptocore.application.service.handler.impl;

import cryptocore.application.mapper.UserMapper;
import cryptocore.application.model.UpdateChat;
import cryptocore.application.model.enums.TelegramCommand;
import cryptocore.application.port.UserServicePort;
import cryptocore.application.service.handler.TelegramCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommandHandler implements TelegramCommandHandler {

    private final UserServicePort userServicePort;
    private final UserMapper userMapper;

    @Override
    public boolean supports(TelegramCommand command) {
        return TelegramCommand.START.equals(command);
    }

    @Override
    public void handle(UpdateChat update) {
        var entity = userMapper.toEntity(update);
        try {
            userServicePort.save(entity);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
