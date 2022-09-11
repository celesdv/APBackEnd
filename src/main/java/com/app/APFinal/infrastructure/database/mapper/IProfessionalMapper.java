package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.ProfessionalRequest;
import com.app.APFinal.aplication.rest.response.ProfessionalResponse;
import com.app.APFinal.infrastructure.database.entity.Professional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProfessionalMapper {

  ProfessionalResponse toProfessionalResponse(Professional professional);

  Professional toProfessional(ProfessionalRequest request);

}
