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
@Table(name = "major_tb")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; // 단과대 이름 ex) 컴퓨터 공학과, 멀티미디어 공학과 ...

    // ================================ //

    @OneToMany(mappedBy = "major", cascade = CascadeType.PERSIST)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "major", cascade = CascadeType.PERSIST)
    private List<User> users;
}
