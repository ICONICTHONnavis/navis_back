package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "subject_tb")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name; // 과목 이름
    @Column
    private String number; // 학수번호
    @Column
    private Boolean is_major; // 전공 여부
    @Column
    private String professor; // 교수 이름
    @Column
    private String semester; // 시행 학기

//====================================================//

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major; // 단과대 정보

    @ManyToOne
    @JoinColumn(name = "subject_type_id")
    private SubjectType subjectType; // 이수 구분

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST)
    private List<Complete> completes; // 수강 과목

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST)
    private List<PreSubject> preSubjects; // 선이수

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST)
    private List<Review> reviews; // 강의평
}

