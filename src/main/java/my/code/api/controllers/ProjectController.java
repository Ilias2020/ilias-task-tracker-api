package my.code.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.api.dto.ProjectDto;
import my.code.api.exceptions.BadRequestException;
import my.code.api.exceptions.NotFoundException;
import my.code.api.factories.ProjectDtoFactory;
import my.code.store.entities.ProjectEntity;
import my.code.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectRepository projectRepository;

    ProjectDtoFactory projectDtoFactory;

    private static final String CREATE_PROJECT = "/api/projects";
    private static final String EDIT_PROJECT = "/api/projects/{project_id}";
    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name){

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't be empty.");
        }

        projectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exist.", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );
        return projectDtoFactory.makeProjectDto(project);
    }

    @PostMapping(EDIT_PROJECT)
    public ProjectDto editPatch(
                                @PathVariable("project_id") Long projectId,
                                @RequestParam String name){

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't be empty.");
        }

        ProjectEntity project = projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                                new NotFoundException(
                                        String.format(
                                                "Project with \"%s\" doesn't exist.",
                                                projectId
                                        )
                                )
                );

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId()))
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exist.", name));
                });

        project.setName(name);

        project = projectRepository.saveAndFlush(project);
        return projectDtoFactory.makeProjectDto(project);
    }
}
