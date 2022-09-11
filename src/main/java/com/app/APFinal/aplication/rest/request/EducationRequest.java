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
public class EducationRequest {

  @Size(max = 50, message = "Maximum size for title is 50 characters.")
  @NotEmpty(message = "The title must not be empty.")
  private String title;

  @Size(max = 50, message = "Maximum size for institution is 50 characters.")
  @NotEmpty(message = "The institution must not be empty.")
  private String institution;

  @Pattern(regexp = RegExpressionUtils.ALPHANUMERIC_CHARACTERS_WITHOUT_BLANK_SPACES,
      message = "The image accepts only alphanumeric characters.")
  private String image;

  @NotEmpty(message = "The institution must not be empty.")
  private String start;

  private String end;

}
