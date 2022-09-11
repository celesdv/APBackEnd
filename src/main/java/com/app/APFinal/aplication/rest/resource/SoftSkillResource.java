package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.SoftSkillRequest;
import com.app.APFinal.aplication.rest.response.ListSoftSkillResponse;
import com.app.APFinal.aplication.rest.response.SoftSkillResponse;
import com.app.APFinal.aplication.service.abstraction.ISoftSkillService;
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
@RequestMapping("softSkills")
@CrossOrigin(origins = "http://localhost:4200/")
public class SoftSkillResource {

  @Autowired
  private ISoftSkillService softSkillService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SoftSkillResponse> create(
      @Valid @RequestBody SoftSkillRequest softSkillRequest){
    SoftSkillResponse softSkillResponse = softSkillService.save(softSkillRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(softSkillResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(softSkillResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    softSkillService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SoftSkillResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(softSkillService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListSoftSkillResponse> listActiveExperience() {
    return ResponseEntity.ok().body(softSkillService.getListSoftSkill());
  }

  @PutMapping("/{id}")
  public ResponseEntity<SoftSkillResponse> update(@PathVariable long id,
      @Valid @RequestBody SoftSkillRequest softSkillRequest) {
    return ResponseEntity.ok(softSkillService.update(id, softSkillRequest));
  }
}
