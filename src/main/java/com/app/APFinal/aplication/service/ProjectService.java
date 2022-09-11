package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.ProjectRequest;
import com.app.APFinal.aplication.rest.response.ListProjectResponse;
import com.app.APFinal.aplication.rest.response.ProjectResponse;
import com.app.APFinal.aplication.service.abstraction.IProjectService;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.entity.Project;
import com.app.APFinal.infrastructure.database.mapper.IProjectMapper;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import com.app.APFinal.infrastructure.database.repository.IProjectRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService implements IProjectService {

  @Autowired
  public IProjectRepository projectRepository;

  @Autowired
  public IProjectMapper projectMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public ProjectResponse save(ProjectRequest request) {
    Project project = projectMapper.toProject(request);
    project.setSoftDeleted(false);
    project.setProfessional(findProfessional());
    return projectMapper.toProjectResponse(projectRepository.save(project));
  }

  @Override
  public void delete(long id) {
    Project project = findBy(id);
    project.setSoftDeleted(true);
    projectRepository.save(project);
  }

  @Override
  public ProjectResponse getBy(long id) {
    Project project = findBy(id);
    return projectMapper.toProjectResponse(project);
  }

  @Override
  public ListProjectResponse getListProject() {
    List<Project> projectList = projectRepository.findAllBySoftDeletedFalse();
    ListProjectResponse listProjectResponse = new ListProjectResponse();
    listProjectResponse.setProjects(projectMapper.toListProjectResponse(projectList));
    return listProjectResponse;
  }

  @Override
  public ProjectResponse update(long id, ProjectRequest request) {
    Project project = findBy(id);
    Project projectUpdated = updateValues(request,project);
    return projectMapper.toProjectResponse(projectRepository.save(projectUpdated));
  }

  private Project findBy(long id) {
    Project project = projectRepository.findByIdAndSoftDeletedFalse(id);
    if (project == null) {
      throw new EntityNotFoundException("Project not found.");
    }
    return project;
  }

  private Project updateValues(ProjectRequest projectRequest, Project project) {
    String name = projectRequest.getName();
    if (name != null) {
      project.setName(name);
    }

    String description = projectRequest.getDescription();
    if (description != null) {
      project.setDescription(description);
    }

    String image = projectRequest.getImage();
    if (image != null) {
      project.setImage(image);
    }

    String link = projectRequest.getLink();
    if (link != null) {
      project.setLink(link);
    }

    return project;
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
