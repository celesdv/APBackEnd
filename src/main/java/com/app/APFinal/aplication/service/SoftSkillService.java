package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.SoftSkillRequest;
import com.app.APFinal.aplication.rest.response.ListSoftSkillResponse;
import com.app.APFinal.aplication.rest.response.SoftSkillResponse;
import com.app.APFinal.aplication.service.abstraction.ISoftSkillService;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.entity.SoftSkill;
import com.app.APFinal.infrastructure.database.mapper.ISoftSkillMapper;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import com.app.APFinal.infrastructure.database.repository.ISoftSkillRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SoftSkillService implements ISoftSkillService {

  @Autowired
  public ISoftSkillRepository softSkillRepository;

  @Autowired
  public ISoftSkillMapper softSkillMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public SoftSkillResponse save(SoftSkillRequest request) {
    SoftSkill softSkill = softSkillMapper.toSoftSkill(request);
    softSkill.setSoftDeleted(false);
    softSkill.setProfessional(findProfessional());
    return softSkillMapper.toSoftSkillResponse(softSkillRepository.save(softSkill));
  }

  @Override
  public void delete(long id) {
    SoftSkill softSkill = findBy(id);
    softSkill.setSoftDeleted(true);
    softSkillRepository.save(softSkill);
  }

  @Override
  public SoftSkillResponse getBy(long id) {
    SoftSkill softSkill = findBy(id);
    return softSkillMapper.toSoftSkillResponse(softSkill);
  }

  @Override
  public ListSoftSkillResponse getListSoftSkill() {
    List<SoftSkill> softSkillList = softSkillRepository.findAllBySoftDeletedFalse();
    ListSoftSkillResponse listSoftSkillResponse = new ListSoftSkillResponse();
    listSoftSkillResponse.setSoftSkills(softSkillMapper.toListSoftSkillResponse(softSkillList));
    return listSoftSkillResponse;
  }

  @Override
  public SoftSkillResponse update(long id, SoftSkillRequest request) {
    SoftSkill softSkill = findBy(id);
    SoftSkill softSkillUpdated = updateValues(request, softSkill);
    return softSkillMapper.toSoftSkillResponse(softSkillRepository.save(softSkillUpdated));
  }

  private SoftSkill findBy(long id) {
    SoftSkill softSkill = softSkillRepository.findByIdAndSoftDeletedFalse(id);
    if (softSkill == null) {
      throw new EntityNotFoundException("Soft Skill not found.");
    }
    return softSkill;
  }

  private SoftSkill updateValues(SoftSkillRequest softSkillRequest, SoftSkill softSkill) {
    String name = softSkillRequest.getName();
    if (name != null) {
      softSkill.setName(name);
    }

    Integer percentage = softSkillRequest.getPercentage();
    if (percentage != null) {
      softSkill.setPercentage(percentage);
    }

    return softSkill;
  }

  private Professional findProfessional(){
    long id = 1;
    Optional<Professional> result = professionalRepository.findById(id);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Professional not found.");
    }
    return result.get();
  }
}
