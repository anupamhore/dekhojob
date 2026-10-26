package io.github.anupamhore.dekhojob.organization;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table (name = "organizations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrganizationType type;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, columnDefinition = "citext")
    private String slug;

    @Column(length = 120)
    private String tagline;

    @Column(length = 2000)
    private String description;

    private String website;

    @Column(length = 100)
    private String industry;

    @Column(length = 20)
    private String sizeRange;

    private Long headquartersLocationId;

    private Short foundedYear;

    @Column(length = 500)
    private String logoKey;

    @Column(length = 500)
    private String bannerKey;

    @Column(name = "is_verified", nullable = false)
    private boolean verified;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;
}
