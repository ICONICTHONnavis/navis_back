package acothon.backend.repository;

import acothon.backend.domain.Chat;
import acothon.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByUser(User user);
}
