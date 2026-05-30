package cryptocore.infrastructure.output.service.impl;

import cryptocore.application.port.UserServicePort;
import cryptocore.infrastructure.output.persistence.entity.User;
import cryptocore.infrastructure.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServicePort {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        var userExists = findByTelegramId(user.getTelegramId());
        if (userExists.isPresent()) {
            log.info("User already created for telegramId {}", user.getTelegramId());
            return;
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }
}
