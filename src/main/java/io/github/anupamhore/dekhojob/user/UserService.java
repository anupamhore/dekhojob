package io.github.anupamhore.dekhojob.user;

import io.github.anupamhore.dekhojob.user.dto.RegisterUserRequest;
import io.github.anupamhore.dekhojob.user.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterUserRequest request);
    UserResponse getById(Long id);
}
