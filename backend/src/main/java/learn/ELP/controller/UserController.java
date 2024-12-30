package learn.ELP.controller;

import jakarta.validation.Valid;
import learn.ELP.dto.request.UserCreationRequest;
import learn.ELP.dto.respone.ApiRespone;
import learn.ELP.dto.respone.UserResponse;
import learn.ELP.service.UserService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    public ApiRespone<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return ApiRespone.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
}