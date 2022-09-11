package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.SoftSkillRequest;
import com.app.APFinal.aplication.rest.response.ListSoftSkillResponse;
import com.app.APFinal.aplication.rest.response.SoftSkillResponse;
import com.app.APFinal.infrastructure.database.entity.SoftSkill;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISoftSkillMapper {

  SoftSkillResponse toSoftSkillResponse(SoftSkill softSkill);

  SoftSkill toSoftSkill(SoftSkillRequest request);

  List<SoftSkillResponse> toListSoftSkillResponse(List<SoftSkill> softSkillList);

}
