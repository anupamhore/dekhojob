package io.github.anupamhore.dekhojob.profile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "educations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "user_id", nullable = false)
    private Profile profile;

    private Long organizationId;

    @Column(nullable = false, length = 150)
    private String schoolName;

    @Column(length = 100)
    private String degree;

    @Column(length = 100)
    private String fieldOfStudy;

    private LocalDate startDate;

    private LocalDate endDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public Education(Profile profile){
        this.profile = profile;
    }

    void setProfile(Profile profile){
        this.profile = profile;
    }
}
