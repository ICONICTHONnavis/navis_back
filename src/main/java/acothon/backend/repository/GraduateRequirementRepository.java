package acothon.backend.repository;

import acothon.backend.domain.GraduateRequirement;
import acothon.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduateRequirementRepository extends JpaRepository<GraduateRequirement,Long> {

    List<GraduateRequirement> findByEnrollmentYear(Long enrollmentYear);
}
