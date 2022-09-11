package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.ProjectRequest;
import com.app.APFinal.aplication.rest.response.ProjectResponse;
import com.app.APFinal.infrastructure.database.entity.Project;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProjectMapper {

  ProjectResponse toProjectResponse(Project project);

  Project toProject(ProjectRequest request);

  List<ProjectResponse> toListProjectResponse(List<Project> projectList);

}
