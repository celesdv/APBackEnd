package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.EducationRequest;
import com.app.APFinal.aplication.rest.response.EducationResponse;
import com.app.APFinal.aplication.rest.response.ListEducationResponse;
import com.app.APFinal.aplication.service.abstraction.IEducationService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("education")
@CrossOrigin(origins = "http://localhost:4200/")
public class EducationResource {

  @Autowired
  private IEducationService educationService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EducationResponse> create(
      @Valid @RequestBody EducationRequest educationRequest){
    EducationResponse educationResponse = educationService.save(educationRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(educationResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(educationResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    educationService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EducationResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(educationService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListEducationResponse> listActiveEducation() {
    return ResponseEntity.ok().body(educationService.getListEducation());
  }

  @PutMapping("/{id}")
  public ResponseEntity<EducationResponse> update(@PathVariable long id,
      @Valid @RequestBody EducationRequest educationRequest) {
    return ResponseEntity.ok(educationService.update(id, educationRequest));
  }
}
