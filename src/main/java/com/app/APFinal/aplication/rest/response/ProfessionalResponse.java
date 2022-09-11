package com.app.APFinal.aplication.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ProfessionalResponse {

  private Long id;
  private String name;
  private String image;
  private String position;
  private String banner;
  private String description;
  private List<EducationResponse> education;
  private List<ExperienceResponse> experience;
  private List<HardSkillResponse> hardSkill;
  private List<ProjectResponse> project;
  private List<SoftSkillResponse> softSkill;
  private List<SocialResponse> social;

}
