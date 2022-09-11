package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.exception.InvalidCredentialsException;
import com.app.APFinal.aplication.exception.UserAlreadyExistException;
import com.app.APFinal.aplication.rest.request.AuthenticationRequest;
import com.app.APFinal.aplication.rest.request.RegisterRequest;
import com.app.APFinal.aplication.rest.response.AuthenticationResponse;
import com.app.APFinal.aplication.rest.response.RegisterResponse;
import com.app.APFinal.aplication.service.abstraction.IAuthenticationService;
import com.app.APFinal.aplication.service.abstraction.IProfessionalService;
import com.app.APFinal.aplication.service.abstraction.IRegisterService;
import com.app.APFinal.infrastructure.database.entity.Role;
import com.app.APFinal.infrastructure.database.entity.User;
import com.app.APFinal.infrastructure.database.mapper.IProfessionalMapper;
import com.app.APFinal.infrastructure.database.mapper.IUserMapper;
import com.app.APFinal.infrastructure.database.repository.IRoleRepository;
import com.app.APFinal.infrastructure.database.repository.IUserRepository;
import com.app.APFinal.infrastructure.spring.config.security.ERole;
import com.app.APFinal.infrastructure.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService, IRegisterService {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IUserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private IRoleRepository roleRepository;


  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {
    if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
      throw new UserAlreadyExistException("Email is already in use.");
    }

    Role userRole = roleRepository.findByName(ERole.USER.getFullRoleName());
    if (userRole == null) {
      throw new EntityNotFoundException("Missing record in role table.");
    }

    User user = userMapper.toUserEntity(registerRequest);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setSoftDeleted(false);
    user.setRole(userRole);
    user = userRepository.save(user);

    RegisterResponse registerResponse = userMapper.toRegisterResponse(user);
    registerResponse.setToken(jwtUtils.generateToken(user));
    return registerResponse;
  }

  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
    Authentication authentication;
    try {
      authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
          authenticationRequest.getEmail(),
          authenticationRequest.getPassword()));
    } catch (Exception e) {
      throw new InvalidCredentialsException("Invalid email or password.");
    }

    String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
    return new AuthenticationResponse(jwt);
  }
}
