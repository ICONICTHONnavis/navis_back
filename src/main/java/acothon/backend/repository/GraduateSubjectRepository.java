package acothon.backend.repository;

import acothon.backend.domain.GraduateRequirement;
import acothon.backend.domain.GraduateSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduateSubjectRepository extends JpaRepository<GraduateSubject,Long> {

    List<GraduateSubject> findByGrade(String grade);
}
