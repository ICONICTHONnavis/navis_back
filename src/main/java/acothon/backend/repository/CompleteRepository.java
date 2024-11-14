package acothon.backend.repository;

import acothon.backend.domain.Chat;
import acothon.backend.domain.Complete;
import acothon.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompleteRepository extends JpaRepository<Complete,Long> {

    List<Complete> findByUser(User user);
    Optional<Complete> findByUserId(Long userId);

}
