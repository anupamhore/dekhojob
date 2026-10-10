package io.github.anupamhore.dekhojob.user.customexception;

public class EmailAlreadyExistsException  extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("An account with the email " + email + " already exists");
    }
}
