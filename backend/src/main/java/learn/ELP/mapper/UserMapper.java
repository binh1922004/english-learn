package learn.ELP.mapper;

import learn.ELP.dto.request.UserCreationRequest;
import learn.ELP.dto.respone.UserResponse;
import learn.ELP.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(Users users);
}
