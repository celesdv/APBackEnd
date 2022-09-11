package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.HardSkillRequest;
import com.app.APFinal.aplication.rest.response.HardSkillResponse;
import com.app.APFinal.infrastructure.database.entity.HardSkill;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHardSkillMapper {

  HardSkillResponse toHardSkillResponse(HardSkill hardSkill);

  HardSkill toHardSkill(HardSkillRequest request);

  List<HardSkillResponse> toListHardSkillResponse(List<HardSkill> hardSkillList);

}
