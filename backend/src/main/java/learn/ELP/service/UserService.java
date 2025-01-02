package learn.ELP.service;

import learn.ELP.dto.request.UserCreationRequest;
import learn.ELP.dto.respone.UserResponse;
import learn.ELP.entity.Users;
import learn.ELP.mapper.UserMapper;
import learn.ELP.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    PasswordEncoder passwordEncoder;
    UsersRepository usersRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        if (usersRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Users user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(usersRepository.save(user));
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
    }
}
