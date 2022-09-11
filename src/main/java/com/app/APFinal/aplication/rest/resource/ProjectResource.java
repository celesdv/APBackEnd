package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.ProjectRequest;
import com.app.APFinal.aplication.rest.response.ListProjectResponse;
import com.app.APFinal.aplication.rest.response.ProjectResponse;
import com.app.APFinal.aplication.service.abstraction.IProjectService;
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
@RequestMapping("projects")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProjectResource {

  @Autowired
  private IProjectService projectService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProjectResponse> create(
      @Valid @RequestBody ProjectRequest projectRequest){
    ProjectResponse projectResponse = projectService.save(projectRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(projectResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(projectResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    projectService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProjectResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(projectService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListProjectResponse> listActiveProjects() {
    return ResponseEntity.ok().body(projectService.getListProject());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectResponse> update(@PathVariable long id,
      @Valid @RequestBody ProjectRequest projectRequest) {
    return ResponseEntity.ok(projectService.update(id, projectRequest));
  }
}
