package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.ExperienceRequest;
import com.app.APFinal.aplication.rest.response.ExperienceResponse;
import com.app.APFinal.aplication.rest.response.ListExperienceResponse;

public interface IExperienceService {

  ExperienceResponse save(ExperienceRequest experience);

  void delete(long id);

  ExperienceResponse getBy(long id);

  ListExperienceResponse getListExperience();

  ExperienceResponse update(long id, ExperienceRequest experience);

}
