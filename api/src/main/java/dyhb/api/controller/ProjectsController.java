package dyhb.api.controller;

import dyhb.api.dto.CreateProjectsDTO;
import dyhb.api.entities.Projects;
import dyhb.api.service.ProjectsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectsController {

    @Autowired
    private final ProjectsService projectsService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Projects>> getProjectsByUserId(UUID userId) {
        List<Projects> projects = projectsService.getProjectsByUserId(userId);
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Projects> addProject(@RequestBody CreateProjectsDTO createProjectsDTO, @PathVariable UUID userId) {
        Projects project = projectsService.addProject(createProjectsDTO, userId);
        if (project == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(project);
    }



}
