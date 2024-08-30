package my.code.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.api.dto.ProjectDto;
import my.code.api.factories.ProjectDtoFactory;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectDtoFactory projectDtoFactory;

    private static final String CREATE_PROJECT = "/api/projects";
    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject();

    @GetMapping
    @PutMapping
    @PatchMapping
    @DeleteMapping
}
