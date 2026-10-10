package io.github.anupamhore.dekhojob.user;

import io.github.anupamhore.dekhojob.user.dto.RegisterUserRequest;
import io.github.anupamhore.dekhojob.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request
    , UriComponentsBuilder uriBuilder){

        UserResponse userResponse = userService.register(request);
        URI location = uriBuilder.path("/api/v1/users/{id}")
                .buildAndExpand(userResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(userResponse);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getById(id);
    }
}
