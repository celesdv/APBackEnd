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
@Builder
public class ProfessionalRequest {

  @Size(max = 50, message = "Maximum size for title is 50 characters.")
  private String name;

  private String image;

  @Size(max = 50, message = "Maximum size for position is 50 characters.")
  private String position;

  private String banner;

  @Size(max = 500, message = "Maximum size for description is 500 characters.")
  private String description;
}
