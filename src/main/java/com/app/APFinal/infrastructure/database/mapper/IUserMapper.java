package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.RegisterRequest;
import com.app.APFinal.aplication.rest.response.RegisterResponse;
import com.app.APFinal.aplication.rest.response.UserResponse;
import com.app.APFinal.infrastructure.database.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {

  User toUserEntity(RegisterRequest registerRequest);

  RegisterResponse toRegisterResponse(User user);

  @Mapping(source = "role.name", target = "role")
  UserResponse toUserResponse(User user);

  List<UserResponse> toListUserResponse(List<User> entities);

}
