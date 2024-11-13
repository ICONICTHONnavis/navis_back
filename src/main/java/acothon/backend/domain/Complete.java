package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "complete_tb")
public class Complete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String grade; // 학생이 받은 학점

    @Column
    private String semester;
    // 수강한 학기 (responseDTO에서 유효성 검사 부분에서 "24-1" 이런 형식으로 받도록 @Valid 구현하자

    //====================================//

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    
}

