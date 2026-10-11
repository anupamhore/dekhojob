package io.github.anupamhore.dekhojob.user;

import io.github.anupamhore.dekhojob.user.dto.RegisterUserRequest;
import io.github.anupamhore.dekhojob.user.dto.UserResponse;

public interface UserService {

    /**
     * Registers a new user. The email is normalised (trimmed, lowercased).
     *
     * @throws EmailAlreadyExistsException if the email is already registered
     */
    UserResponse register(RegisterUserRequest request);

    /**
     * Return the user based on its id
     * @throws UserNotFoundException if the user does not exist
     */
    UserResponse getById(Long id);
}
