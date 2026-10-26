package io.github.anupamhore.dekhojob.profile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Profile profile;

    private Long organizationId;

    @Column(nullable = false, length = 150)
    private String companyName;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EmploymentType employmentType;

    private Long locationId;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private WorkplaceType workplaceType;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 2000)
    private String description;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Experience(String companyName, String title, LocalDate startDate) {
        this.companyName = companyName;
        this.title = title;
        this.startDate = startDate;
    }

    void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isCurrent(){
        return endDate == null;
    }
}
