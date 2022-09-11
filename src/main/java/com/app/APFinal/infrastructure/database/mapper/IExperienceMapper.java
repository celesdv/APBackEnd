package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.ExperienceRequest;
import com.app.APFinal.aplication.rest.response.ExperienceResponse;
import com.app.APFinal.infrastructure.database.entity.Experience;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IExperienceMapper {

  ExperienceResponse toExperienceResponse(Experience experience);

  Experience toExperience(ExperienceRequest request);

  List<ExperienceResponse> toListExperienceResponse(List<Experience> experienceList);

}
