package my.code.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.api.dto.ProjectDto;
import my.code.api.exceptions.BadRequestException;
import my.code.api.factories.ProjectDtoFactory;
import my.code.store.entities.ProjectEntity;
import my.code.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectRepository projectRepository;

    ProjectDtoFactory projectDtoFactory;

    private static final String CREATE_PROJECT = "/api/projects";
    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name){

        ProjectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exist.", name));
                });


        ProjectEntity project =
        //return projectDtoFactory.makeProjectDto();
        return null;
    }

}
