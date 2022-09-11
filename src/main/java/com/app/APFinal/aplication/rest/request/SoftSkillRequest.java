package com.app.APFinal.aplication.rest.request;

import com.app.APFinal.aplication.util.RegExpressionUtils;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoftSkillRequest {

  @Size(max = 20, message = "Maximum size for title is 20 characters.")
  @NotEmpty(message = "The name must not be empty.")
  private String name;

  private Integer percentage;

}
