package acothon.backend.repository;

import acothon.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByStudentNumber(Long studentNumber);

    Optional<User> findByStudentNumber(Long studentNumber);



}
