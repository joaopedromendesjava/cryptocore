package cryptocore.application.port;

import cryptocore.infrastructure.output.persistence.entity.User;

import java.util.Optional;

public interface UserServicePort {
    void save(User user);

    Optional<User> findByTelegramId(Long telegramId);
}
