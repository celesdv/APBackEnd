package com.app.APFinal.aplication.rest.request;

import com.app.APFinal.aplication.util.RegExpressionUtils;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class SocialRequest {

  @NotEmpty(message = "The image must not be empty.")
  @Pattern(regexp = RegExpressionUtils.ALPHANUMERIC_CHARACTERS_WITHOUT_BLANK_SPACES,
      message = "The image accepts only alphanumeric characters.")
  private String image;

  @NotEmpty(message = "The link must not be empty.")
  @Pattern(regexp = RegExpressionUtils.URL,
      message = "The link accepts only URL format.")
  private String link;

}
