package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "subject_type_tb")
public class SubjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; // 전공, 학문 기초, MSC ...


}
