package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.EducationRequest;
import com.app.APFinal.aplication.rest.response.EducationResponse;
import com.app.APFinal.aplication.rest.response.ListEducationResponse;
import com.app.APFinal.aplication.service.abstraction.IEducationService;
import com.app.APFinal.infrastructure.database.entity.Education;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.mapper.IEducationMapper;
import com.app.APFinal.infrastructure.database.repository.IEducationRepository;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationService implements IEducationService {

  @Autowired
  public IEducationRepository educationRepository;

  @Autowired
  public IEducationMapper educationMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public EducationResponse save(EducationRequest educationRequest) {
    Education education = educationMapper.toEducation(educationRequest);
    education.setSoftDeleted(false);
    education.setProfessional(findProfessional());
    return educationMapper.toEducationResponse(educationRepository.save(education));
  }

  @Override
  public void delete(long id) {
    Education education = findBy(id);
    education.setSoftDeleted(true);
    educationRepository.save(education);
  }

  @Override
  public EducationResponse getBy(long id) {
    Education education = findBy(id);
    return educationMapper.toEducationResponse(education);
  }

  @Override
  public ListEducationResponse getListEducation() {
    List<Education> educationList = educationRepository.findAllBySoftDeletedFalse();
    ListEducationResponse listEducationResponse = new ListEducationResponse();
    listEducationResponse.setEducations(educationMapper.toListEducationResponse(educationList));
    return listEducationResponse;
  }

  @Override
  public EducationResponse update(long id, EducationRequest educationRequest) {
    Education education = findBy(id);
    Education educationUpdated = updateValues(educationRequest, education);
    return educationMapper.toEducationResponse(educationRepository.save(educationUpdated));
  }

  private Education findBy(long id) {
    Education education = educationRepository.findByIdAndSoftDeletedFalse(id);
    if (education == null) {
      throw new EntityNotFoundException("Education not found.");
    }
    return education;
  }

  private Education updateValues(EducationRequest educationRequest, Education education) {
    String title = educationRequest.getTitle();
    if (title != null) {
      education.setTitle(title);
    }

    String institution = educationRequest.getInstitution();
    if (institution != null) {
      education.setInstitution(institution);
    }

    String image = educationRequest.getImage();
    if (image != null) {
      education.setImage(image);
    }

    String start = educationRequest.getStart();
    if (start != null) {
      education.setStart(start);
    }

    String end = educationRequest.getEnd();
    if (end != null) {
      education.setEnd(end);
    }

    return education;
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
