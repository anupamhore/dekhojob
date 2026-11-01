package io.github.anupamhore.dekhojob.user;

public record UserRegisteredEvent(Long userId, String firstName, String lastName) {
}
