package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.util.List;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentName; // 이름

    @Column
    private Long studentNumber; // 학번
    @Column
    private String password; // 비번

    @Column
    private String admission; // 입학년도

    //====================================//

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Chat> chats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Complete> completes;

    @Builder
    public User(String studentName, Long studentNumber, String password, String admission, Major major) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.password = password;
        this.admission = admission;
        this.major = major;
    }
}

