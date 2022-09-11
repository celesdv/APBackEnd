package com.app.APFinal.aplication.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String role;

}
