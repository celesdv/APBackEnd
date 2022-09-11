package com.app.APFinal.aplication.rest.request;

import com.app.APFinal.aplication.util.RegExpressionUtils;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class RegisterRequest {


  @NotNull(message = "The first name must not be null")
  private String firstName;

  @NotNull(message = "The last name must not be null")
  private String lastName;

  @NotNull(message = "The email must not be null")
  @Email(message = "The email has invalid format.")
  private String email;

  @NotNull(message = "The password must not be null")
  @Length(min = 6, max = 8, message = "The password must be between 6 and 8 characters.")
  private String password;

}
