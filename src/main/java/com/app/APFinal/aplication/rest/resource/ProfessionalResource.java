package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.ProfessionalRequest;
import com.app.APFinal.aplication.rest.response.ProfessionalResponse;
import com.app.APFinal.aplication.service.abstraction.IProfessionalService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portfolio")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProfessionalResource {

  @Autowired
  private IProfessionalService professionalService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProfessionalResponse> getPortfolio() {
    return ResponseEntity.ok().body(professionalService.getPublicDetails());
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProfessionalResponse> update(@Valid @RequestBody
  ProfessionalRequest professionalRequest) {
    return ResponseEntity.ok().body(professionalService.update(professionalRequest));
  }
}
