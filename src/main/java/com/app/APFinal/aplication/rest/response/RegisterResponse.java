package com.app.APFinal.aplication.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String token;

}
