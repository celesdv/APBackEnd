package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.AuthenticationRequest;
import com.app.APFinal.aplication.rest.request.RegisterRequest;
import com.app.APFinal.aplication.rest.response.AuthenticationResponse;
import com.app.APFinal.aplication.rest.response.RegisterResponse;
import com.app.APFinal.aplication.rest.response.UserResponse;
import com.app.APFinal.aplication.service.abstraction.IAuthenticationService;
import com.app.APFinal.aplication.service.abstraction.IRegisterService;
import com.app.APFinal.aplication.service.abstraction.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticationResource {

  @Autowired
  private IAuthenticationService authService;

  @Autowired
  private IRegisterService registerService;

  @Autowired
  private IUserService userService;

  @PostMapping(path = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegisterResponse> register(
      @Valid @RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(registerService.register(registerRequest));
  }

  @PostMapping(path = "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponse> login(
      @Valid @RequestBody AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok().body(authService.login(authenticationRequest));
  }

  @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse> getUser() {
    return ResponseEntity.ok().body(userService.getUserAuthenticated());
  }
}
