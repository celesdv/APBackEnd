package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.ProfessionalRequest;
import com.app.APFinal.aplication.rest.response.ProfessionalResponse;
import com.app.APFinal.aplication.service.abstraction.IEducationService;
import com.app.APFinal.aplication.service.abstraction.IExperienceService;
import com.app.APFinal.aplication.service.abstraction.IHardSkillService;
import com.app.APFinal.aplication.service.abstraction.IProfessionalService;
import com.app.APFinal.aplication.service.abstraction.IProjectService;
import com.app.APFinal.aplication.service.abstraction.ISocialService;
import com.app.APFinal.aplication.service.abstraction.ISoftSkillService;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.mapper.IProfessionalMapper;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfessionalService implements IProfessionalService {

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Autowired
  public IProfessionalMapper professionalMapper;

  @Autowired
  public IEducationService educationService;

  @Autowired
  public IExperienceService experienceService;

  @Autowired
  public IHardSkillService hardSkillService;

  @Autowired
  public ISoftSkillService softSkillService;

  @Autowired
  public IProjectService projectService;

  @Autowired
  public ISocialService socialService;

  @Override
  public ProfessionalResponse getPublicDetails() {
    ProfessionalResponse professionalResponse =
        professionalMapper.toProfessionalResponse(findProfessional());
    professionalResponse.setEducation(educationService.getListEducation().getEducations());
    professionalResponse.setExperience(experienceService.getListExperience().getExperiences());
    professionalResponse.setHardSkill(hardSkillService.getListHardSkill().getHardSkills());
    professionalResponse.setSoftSkill(softSkillService.getListSoftSkill().getSoftSkills());
    professionalResponse.setProject(projectService.getListProject().getProjects());
    professionalResponse.setSocial(socialService.getListSocial().getSocials());
    return professionalResponse;
  }

  @Override
  public ProfessionalResponse update(ProfessionalRequest request) {
    Professional professional = updateValues(request, findProfessional());
    professionalRepository.save(professional);

    return professionalMapper.toProfessionalResponse(professional);
  }

  private Professional findProfessional(){
    List<Professional> professionals = professionalRepository.findAll();
    if (professionals.isEmpty()) {
      throw new EntityNotFoundException("Missing record in organization table.");
    }
    return professionals.get(0);
  }

  private Professional findBy(String name) {
    Professional professional = professionalRepository.findByName(name);
    if (professional == null) {
      throw new EntityNotFoundException("Professional not found.");
    }
    return professional;
  }

  private Professional updateValues(ProfessionalRequest professionalRequest, Professional professional) {
    String name = professionalRequest.getName();
    if (name != null) {
      professional.setName(name);
    }

    String image = professionalRequest.getImage();
    if (image != null) {
      professional.setImage(image);
    }

    String position = professionalRequest.getPosition();
    if (position != null) {
      professional.setPosition(position);
    }

    String banner = professionalRequest.getBanner();
    if (banner != null) {
      professional.setBanner(banner);
    }

    String description = professionalRequest.getDescription();
    if (description != null) {
      professional.setDescription(description);
    }

    return professional;
  }

}
