package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "graduate_subject_tb")
public class GraduateSubject {

    // ex) 20학번이 들어야 하는 필수 과목들에 대한 정보를 저장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String grade; // 학번 (사용자 학번 아님, 대상 학번이 들어야 하는 정보)

    @Column
    private String requiredSubjectName; // 졸업 필수 과목

    @Column
    private Boolean isMajor;
    // Getters and Setters
}
