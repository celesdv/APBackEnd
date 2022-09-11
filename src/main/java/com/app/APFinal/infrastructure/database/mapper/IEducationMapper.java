package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.EducationRequest;
import com.app.APFinal.aplication.rest.response.EducationResponse;
import com.app.APFinal.infrastructure.database.entity.Education;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEducationMapper {

  EducationResponse toEducationResponse(Education education);

  Education toEducation(EducationRequest request);

  List<EducationResponse> toListEducationResponse(List<Education> educationList);

}
