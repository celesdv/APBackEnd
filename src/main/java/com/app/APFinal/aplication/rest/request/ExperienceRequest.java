package com.app.APFinal.aplication.rest.request;

import com.app.APFinal.aplication.util.RegExpressionUtils;
import javax.persistence.Column;
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
public class ExperienceRequest {

  @Size(max = 50, message = "Maximum size for position is 50 characters.")
  @NotEmpty(message = "The position must not be empty.")
  private String position;

  @Size(max = 50, message = "Maximum size for company name is 50 characters.")
  @NotEmpty(message = "The company must not be empty.")
  private String company;

  @Pattern(regexp = RegExpressionUtils.ALPHANUMERIC_CHARACTERS_WITHOUT_BLANK_SPACES,
      message = "The image accepts only alphanumeric characters.")
  private String image;

  @NotEmpty(message = "The start date must not be empty.")
  private String start;

  private String end;

  private String description;

}
