package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "graduation_requirement_tb")
public class GraduateRequirement {

    // 입학 년도별 졸업에 필요한 총 학점 정보

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long enrollmentYear; // 유저의 입학년도
    @Column
    private Long majorCredits; // 들어야 하는 전공 총 학점
    @Column
    private Long mscCredits; // 들어야하는 msc 총 학점
    @Column
    private Long generalCredits; // 들어야 하는 일반교양 총 학점
    @Column
    private Long requiredCredits; // 들어야 하는 총 학점 = majorCredits + mscCredits + generalCredits

}

