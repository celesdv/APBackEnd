package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.ProjectRequest;
import com.app.APFinal.aplication.rest.response.ListProjectResponse;
import com.app.APFinal.aplication.rest.response.ProjectResponse;

public interface IProjectService {

  ProjectResponse save(ProjectRequest project);

  void delete(long id);

  ProjectResponse getBy(long id);

  ListProjectResponse getListProject();

  ProjectResponse update(long id, ProjectRequest project);

}
