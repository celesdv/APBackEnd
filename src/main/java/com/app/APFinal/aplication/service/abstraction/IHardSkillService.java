package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.HardSkillRequest;
import com.app.APFinal.aplication.rest.response.HardSkillResponse;
import com.app.APFinal.aplication.rest.response.ListHardSkillResponse;

public interface IHardSkillService {

  HardSkillResponse save(HardSkillRequest hardSkill);

  void delete(long id);

  HardSkillResponse getBy(long id);

  ListHardSkillResponse getListHardSkill();

  HardSkillResponse update(long id, HardSkillRequest hardSkill);

}
