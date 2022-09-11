package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.HardSkillRequest;
import com.app.APFinal.aplication.rest.response.HardSkillResponse;
import com.app.APFinal.aplication.rest.response.ListHardSkillResponse;
import com.app.APFinal.aplication.service.abstraction.IHardSkillService;
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
@RequestMapping("hardSkills")
@CrossOrigin(origins = "http://localhost:4200/")
public class HardSkillResource {

  @Autowired
  private IHardSkillService hardSkillService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HardSkillResponse> create(
      @Valid @RequestBody HardSkillRequest hardSkillRequest){
    HardSkillResponse hardSkillResponse = hardSkillService.save(hardSkillRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(hardSkillResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(hardSkillResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    hardSkillService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HardSkillResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(hardSkillService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListHardSkillResponse> listActiveHardSkill() {
    return ResponseEntity.ok().body(hardSkillService.getListHardSkill());
  }

  @PutMapping("/{id}")
  public ResponseEntity<HardSkillResponse> update(@PathVariable long id,
      @Valid @RequestBody HardSkillRequest hardSkillRequest) {
    return ResponseEntity.ok(hardSkillService.update(id, hardSkillRequest));
  }
}
