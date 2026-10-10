package io.github.anupamhore.dekhojob.user;

import io.github.anupamhore.dekhojob.user.customexception.EmailAlreadyExistsException;
import io.github.anupamhore.dekhojob.user.customexception.UserNotFoundException;
import io.github.anupamhore.dekhojob.user.dto.RegisterUserRequest;
import io.github.anupamhore.dekhojob.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterUserRequest request) {

        //why this because in countries like Turkey, the I.tolowerCase(), gives i without dot
        //so the mismatch can happen hence, Locale.ROOT
        String email = request.email().trim().toLowerCase(Locale.ROOT);

        if(userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User(
                email,
                passwordEncoder.encode(request.password()),
                request.firstName().trim(),
                request.lastName().trim()
        );

        return UserResponse.from(userRepository.save(user));

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
