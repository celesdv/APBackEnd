package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.ProfessionalRequest;
import com.app.APFinal.aplication.rest.response.ProfessionalResponse;

public interface IProfessionalService {

  ProfessionalResponse getPublicDetails();

  ProfessionalResponse update(ProfessionalRequest request);

}
