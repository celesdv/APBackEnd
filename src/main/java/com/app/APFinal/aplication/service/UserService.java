package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.UserRequest;
import com.app.APFinal.aplication.rest.response.ListUsersResponse;
import com.app.APFinal.aplication.rest.response.UserResponse;
import com.app.APFinal.aplication.service.abstraction.IUserService;
import com.app.APFinal.aplication.util.SecurityUtils;
import com.app.APFinal.infrastructure.database.entity.User;
import com.app.APFinal.infrastructure.database.mapper.IUserMapper;
import com.app.APFinal.infrastructure.database.repository.IUserRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IUserMapper userMapper;

  @Autowired
  private SecurityUtils securityUtils;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) {
    return getUser(email);
  }

  @Override
  public void delete(Long id) {
    User user = findBy(id);
    user.setSoftDeleted(true);
    userRepository.save(user);
  }

  @Override
  public void update(Long id, UserRequest userRequest) {
    User user = findBy(id);
    User userUpdated = updateValues(userRequest, user);
    userRepository.save(userUpdated);
  }

  @Override
  public ListUsersResponse listActiveUsers() {
    List<User> listUserEntities = userRepository.findAllActiveUsers();
    ListUsersResponse listUsersResponse = new ListUsersResponse();
    listUsersResponse.setUsers(userMapper.toListUserResponse(listUserEntities));
    return listUsersResponse;
  }

  @Override
  public UserResponse getUserAuthenticated() {
    return userMapper.toUserResponse((User) securityUtils.getUserAuthenticated());
  }

  private User getUser(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found.");
    }
    return user;
  }

  private User findBy(Long id) {
    Optional<User> optionalUserEntity = userRepository.findById(id);
    if (optionalUserEntity.isEmpty()
        || Boolean.TRUE.equals(optionalUserEntity.get().getSoftDeleted())) {
      throw new EntityNotFoundException("User not found.");
    }
    return optionalUserEntity.get();
  }

  private User updateValues(UserRequest userRequest, User userEntity) {
    String firstName = userRequest.getFirstName();
    if (firstName != null) {

      userEntity.setFirstName(firstName);
    }

    String lastName = userRequest.getLastName();
    if (lastName != null) {
      userEntity.setLastName(lastName);
    }

    String password = userRequest.getPassword();
    if (password != null) {
      userEntity.setPassword(passwordEncoder.encode(password));
    }

    return userEntity;
  }
}
