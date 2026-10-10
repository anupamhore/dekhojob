package io.github.anupamhore.dekhojob.user.customexception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with the id " + id + " does not exist");
    }
}
