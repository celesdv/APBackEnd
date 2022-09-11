package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.EducationRequest;
import com.app.APFinal.aplication.rest.response.EducationResponse;
import com.app.APFinal.aplication.rest.response.ListEducationResponse;

public interface IEducationService {

  EducationResponse save(EducationRequest education);

  void delete(long id);

  EducationResponse getBy(long id);

  ListEducationResponse getListEducation();

  EducationResponse update(long id, EducationRequest education);

}
