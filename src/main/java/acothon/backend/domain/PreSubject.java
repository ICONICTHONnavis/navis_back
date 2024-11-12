package acothon.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "pre_subject_tb")
public class PreSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// ================================= //

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pre_subject_id", nullable = false)
    private Subject preSubject;

}

