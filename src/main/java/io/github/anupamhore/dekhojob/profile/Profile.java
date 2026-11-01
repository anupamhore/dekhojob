package io.github.anupamhore.dekhojob.profile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.Order;

import java.time.Instant;
import java.util.*;

@Entity
@Table (name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {


    @Version
    private Long version;

    @Id
    private Long userId;

    @Column(nullable = false, columnDefinition = "citext")
    private String slug;

    @Column(length = 220)
    private String headline;

    @Column(length = 2600)
    private String about;

    private Long locationId;

    @Column(length = 500)
    private String photoKey;

    @Column(length = 500)
    private String bannerKey;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startDate DESC")
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "profile" , cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startDate DESC")
    private List<Education>  educations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "profiles_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public Profile (Long userId, String slug){
        this.userId = userId;
        this.slug = slug;
    }

    public void addExperience(Experience experience){
        experiences.add(experience);
        experience.setProfile(this);
    }

    public void removeExperience(Experience experience){
        experiences.remove(experience);
        experience.setProfile(null);
    }

    public void addEducation(Education education){
        educations.add(education);
        education.setProfile(this);
    }

    public void removeEducation(Education education){
        educations.remove(education);
        education.setProfile(null);
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }

    public void removeSkill(Skill skill){
        skills.remove(skill);
    }

    public List<Experience> getExperiences() {
        return Collections.unmodifiableList(experiences);
    }

    public List<Education> getEducations() {
        return Collections.unmodifiableList(educations);
    }

    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }
}
