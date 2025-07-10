package dyhb.api.service;

import dyhb.api.dto.CreateProjectsDTO;
import dyhb.api.database.models.Projects;
import dyhb.api.database.repository.ProjectsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectsService {

    @Autowired
    private final ProjectsRepository projectsRepository;

    public List<Projects> getProjectsByUserId(UUID userId) {
        return projectsRepository.findByUserId(userId);
    }


    public Projects addProject(CreateProjectsDTO createProjectsDTO, UUID userId) {
        Projects project = new Projects();

        project.setName(createProjectsDTO.getName());
        project.setDescription(createProjectsDTO.getDescription());

        project.setUserId(userId);

        return projectsRepository.save(project);
    }
}
