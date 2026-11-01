package io.github.anupamhore.dekhojob.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


    @Query("select p from Profile p left join fetch p.experiences")
    List<Profile> findAllWithExperiences();

    boolean existsBySlug(String slug);
}
