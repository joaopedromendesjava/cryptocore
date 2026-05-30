package cryptocore.infrastructure.output.persistence.repository;

import cryptocore.infrastructure.output.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.telegramId = :telegramId")
    Optional<User> findByTelegramId(@Param("telegramId") Long telegramId);
}
