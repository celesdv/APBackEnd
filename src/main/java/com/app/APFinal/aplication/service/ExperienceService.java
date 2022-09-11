package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.ExperienceRequest;
import com.app.APFinal.aplication.rest.response.ExperienceResponse;
import com.app.APFinal.aplication.rest.response.ListExperienceResponse;
import com.app.APFinal.aplication.service.abstraction.IExperienceService;
import com.app.APFinal.infrastructure.database.entity.Experience;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.mapper.IExperienceMapper;
import com.app.APFinal.infrastructure.database.repository.IExperienceRepository;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExperienceService implements IExperienceService {

  @Autowired
  public IExperienceRepository experienceRepository;

  @Autowired
  public IExperienceMapper experienceMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public ExperienceResponse save(ExperienceRequest request) {
    Experience experience = experienceMapper.toExperience(request);
    experience.setSoftDeleted(false);
    experience.setProfessional(findProfessional());
    return experienceMapper.toExperienceResponse(experienceRepository.save(experience));
  }

  @Override
  public void delete(long id) {
    Experience experience = findBy(id);
    experience.setSoftDeleted(true);
    experienceRepository.save(experience);
  }

  @Override
  public ExperienceResponse getBy(long id) {
    Experience experience = findBy(id);
    return experienceMapper.toExperienceResponse(experience);
  }

  @Override
  public ListExperienceResponse getListExperience() {
    List<Experience> experienceList = experienceRepository.findAllBySoftDeletedFalse();
    ListExperienceResponse listExperienceResponse = new ListExperienceResponse();
    listExperienceResponse.setExperiences(experienceMapper.toListExperienceResponse(experienceList));
    return listExperienceResponse;
  }

  @Override
  public ExperienceResponse update(long id, ExperienceRequest request) {
    Experience experience = findBy(id);
    Experience experienceUpdated = updateValues(request, experience);
    return experienceMapper.toExperienceResponse(experienceRepository.save(experienceUpdated));
  }

  private Experience findBy(long id) {
    Experience experience = experienceRepository.findByIdAndSoftDeletedFalse(id);
    if (experience == null) {
      throw new EntityNotFoundException("Experience not found.");
    }
    return experience;
  }

  private Experience updateValues(ExperienceRequest experienceRequest, Experience experience) {
    String position = experienceRequest.getPosition();
    if (position != null) {
      experience.setPosition(position);
    }

    String company = experienceRequest.getCompany();
    if (company != null) {
      experience.setCompany(company);
    }

    String image = experienceRequest.getImage();
    if (image != null) {
      experience.setImage(image);
    }

    String start = experienceRequest.getStart();
    if (start != null) {
      experience.setStart(start);
    }

    String end = experienceRequest.getEnd();
    if (end != null) {
      experience.setEnd(end);
    }

    String description = experienceRequest.getDescription();
    if (description != null) {
      experience.setDescription(description);
    }

    return experience;
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
