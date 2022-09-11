package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.UserRequest;
import com.app.APFinal.aplication.rest.response.ListUsersResponse;
import com.app.APFinal.aplication.rest.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

  UserDetails loadUserByUsername(String email);

  void delete(Long id);

  void update(Long id, UserRequest userRequest);

  ListUsersResponse listActiveUsers();

  UserResponse getUserAuthenticated();

}
