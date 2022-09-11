package com.app.APFinal.aplication.rest.request;

import com.app.APFinal.aplication.util.RegExpressionUtils;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequest {

  @Nullable
  private String firstName;

  @Nullable
  private String lastName;

  @Nullable
  @Length(min = 6, max = 8, message = "The password must be between 6 and 8 characters.")
  private String password;

}
