package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.ExperienceRequest;
import com.app.APFinal.aplication.rest.response.ExperienceResponse;
import com.app.APFinal.aplication.rest.response.ListExperienceResponse;
import com.app.APFinal.aplication.service.abstraction.IExperienceService;
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
@RequestMapping("experience")
@CrossOrigin(origins = "http://localhost:4200/")
public class ExperienceResource {

  @Autowired
  private IExperienceService experienceService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExperienceResponse> create(
      @Valid @RequestBody ExperienceRequest experienceRequest){
    ExperienceResponse experienceResponse = experienceService.save(experienceRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(experienceResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(experienceResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    experienceService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExperienceResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(experienceService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListExperienceResponse> listActiveExperience() {
    return ResponseEntity.ok().body(experienceService.getListExperience());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExperienceResponse> update(@PathVariable long id,
      @Valid @RequestBody ExperienceRequest experienceRequest) {
    return ResponseEntity.ok(experienceService.update(id, experienceRequest));
  }
}
