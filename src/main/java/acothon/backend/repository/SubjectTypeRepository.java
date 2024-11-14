package acothon.backend.repository;

import acothon.backend.domain.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectTypeRepository extends JpaRepository<SubjectType, Long> {
    Optional<SubjectType> findSubjectTypeByName(String name);
}
