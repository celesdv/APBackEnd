package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.HardSkillRequest;
import com.app.APFinal.aplication.rest.response.HardSkillResponse;
import com.app.APFinal.aplication.rest.response.ListHardSkillResponse;
import com.app.APFinal.aplication.service.abstraction.IHardSkillService;
import com.app.APFinal.infrastructure.database.entity.HardSkill;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.mapper.IHardSkillMapper;
import com.app.APFinal.infrastructure.database.repository.IHardSkillRepository;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HardSkillService implements IHardSkillService {

  @Autowired
  public IHardSkillRepository hardSkillRepository;

  @Autowired
  public IHardSkillMapper hardSkillMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public HardSkillResponse save(HardSkillRequest request) {
    HardSkill hardSkill = hardSkillMapper.toHardSkill(request);
    hardSkill.setSoftDeleted(false);
    hardSkill.setProfessional(findProfessional());
    return hardSkillMapper.toHardSkillResponse(hardSkillRepository.save(hardSkill));
  }

  @Override
  public void delete(long id) {
    HardSkill hardSkill = findBy(id);
    hardSkill.setSoftDeleted(true);
    hardSkillRepository.save(hardSkill);
  }

  @Override
  public HardSkillResponse getBy(long id) {
    HardSkill hardSkill = findBy(id);
    return hardSkillMapper.toHardSkillResponse(hardSkill);
  }

  @Override
  public ListHardSkillResponse getListHardSkill() {
    List<HardSkill> hardSkillList = hardSkillRepository.findAllBySoftDeletedFalse();
    ListHardSkillResponse listHardSkillResponse = new ListHardSkillResponse();
    listHardSkillResponse.setHardSkills(hardSkillMapper.toListHardSkillResponse(hardSkillList));
    return listHardSkillResponse;
  }

  @Override
  public HardSkillResponse update(long id, HardSkillRequest request) {
    HardSkill hardSkill = findBy(id);
    HardSkill hardSkillUpdated = updateValues(request, hardSkill);
    return hardSkillMapper.toHardSkillResponse(hardSkillRepository.save(hardSkillUpdated));
  }

  private HardSkill findBy(long id) {
    HardSkill hardSkill = hardSkillRepository.findByIdAndSoftDeletedFalse(id);
    if (hardSkill == null) {
      throw new EntityNotFoundException("Hard Skill not found.");
    }
    return hardSkill;
  }

  private HardSkill updateValues(HardSkillRequest hardSkillRequest, HardSkill hardSkill) {
    String image = hardSkillRequest.getImage();
    if (image != null) {
      hardSkill.setImage(image);
    }

    Integer percentage = hardSkillRequest.getPercentage();
    if (percentage != null) {
      hardSkill.setPercentage(percentage);
    }

    return hardSkill;
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
