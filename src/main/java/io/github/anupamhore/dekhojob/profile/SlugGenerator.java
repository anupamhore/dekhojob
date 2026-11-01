package io.github.anupamhore.dekhojob.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class SlugGenerator {

    private static final int MAX_BASE_LENGTH = 50;
    private static final int MAX_ATTEMPTS = 5;
    private static final String SUFFIX_CHARS="abcdefghijklmnopqrstuvwxyz0123456789";

    private final ProfileRepository profileRepository;

    String generator(String firstName, String lastName) {
        String base = toSlug(firstName + " " + lastName);
        if(!profileRepository.existsBySlug(base)){
            return base;
        }
        for(int attempt = 0; attempt < MAX_ATTEMPTS; attempt++){
            String candidate = base + "-" + randomSuffix(4);
            if(!profileRepository.existsBySlug(candidate)){
                return candidate;
            }
        }
        throw new IllegalStateException("Could not generate a unique slug for: " + base);
    }

    static String toSlug(String input){
        String withoutAccents = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}","");

        String slug = withoutAccents.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)","");

        if(slug.length() > MAX_BASE_LENGTH){
            slug = slug.substring(0, MAX_BASE_LENGTH).replaceAll("-$","");
        }
        return slug.isEmpty() ? "member" : slug;
    }

    private static String randomSuffix(int length) {
        StringBuilder suffix = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            suffix.append(SUFFIX_CHARS.charAt(
                    ThreadLocalRandom.current().nextInt(SUFFIX_CHARS.length())
            ));
        }
        return suffix.toString();
    }
}
