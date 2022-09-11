package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.SoftSkillRequest;
import com.app.APFinal.aplication.rest.response.ListSoftSkillResponse;
import com.app.APFinal.aplication.rest.response.SoftSkillResponse;

public interface ISoftSkillService {

  SoftSkillResponse save(SoftSkillRequest softSkill);

  void delete(long id);

  SoftSkillResponse getBy(long id);

  ListSoftSkillResponse getListSoftSkill();

  SoftSkillResponse update(long id, SoftSkillRequest softSkill);

}
