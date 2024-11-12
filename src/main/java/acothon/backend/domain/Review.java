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
@Table(name = "review_tb")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long score;

    // ================================= //

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}

