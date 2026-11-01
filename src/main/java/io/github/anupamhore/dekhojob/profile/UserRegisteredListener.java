package io.github.anupamhore.dekhojob.profile;

import io.github.anupamhore.dekhojob.user.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisteredListener {
    private  final ProfileRepository profileRepository;
    private  final SlugGenerator slugGenerator;

    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        String slug = slugGenerator.generator(event.firstName(), event.lastName());
        profileRepository.save(new Profile(event.userId(),  slug));
    }
}
