package pl.betse.beontime.projectservice.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.betse.beontime.projectservice.bo.ProjectBo;
import pl.betse.beontime.projectservice.mapper.ProjectMapper;
import pl.betse.beontime.projectservice.model.ProjectBody;
import pl.betse.beontime.projectservice.service.ProjectService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectMapper projectMapper;
    private final ProjectService projectService;

    public ProjectController(ProjectMapper projectMapper, ProjectService projectService) {
        this.projectMapper = projectMapper;
        this.projectService = projectService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<Resource<ProjectBody>> getProjectByGuid(@PathVariable String guid) {
        ProjectBo projectBo = projectService.getProjectByGuid(guid);
        ProjectBody projectBody = projectMapper.mapProjectBoToProjectBody(projectBo);
        addLinks(projectBody);
        return ResponseEntity.ok(new Resource<>(projectBody));
    }

    @GetMapping
    public ResponseEntity<Resources<ProjectBody>> getProjectByDepartment(@RequestParam(name = "department") String department) {
        List<ProjectBody> projects = projectService.getProjectsByDepartmentId(department).stream()
                .map(projectMapper::mapProjectBoToProjectBody)
                .collect(Collectors.toList());
        projects.forEach(this::addLinks);
        return ResponseEntity.ok(new Resources<>(projects));
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody ProjectBody projectBody) {
        ProjectBo projectBo = projectService.addNewProject(projectMapper.mapProjectBodyToProjectBo(projectBody));
        URI location = linkTo(methodOn(ProjectController.class).getProjectByGuid(projectBo.getId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{guid}")
    public ResponseEntity updateProject(@PathVariable("guid") String projectGuid, @RequestBody ProjectBody projectBody) {
        projectMapper.mapProjectBoToProjectBody(projectService.updateProject(projectGuid, projectMapper.mapProjectBodyToProjectBo(projectBody)));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity deleteProject(@PathVariable("guid") String guid) {
        projectService.deleteProjectByGuid(guid);
        return ResponseEntity.ok().build();
    }

    private void addLinks(ProjectBody projectBody) {
        projectBody.add(linkTo(methodOn(ProjectController.class).getProjectByGuid(projectBody.getProjectId())).withSelfRel());
        projectBody.add(linkTo(methodOn(ProjectController.class).deleteProject(projectBody.getProjectId())).withRel("DELETE"));
    }

}
