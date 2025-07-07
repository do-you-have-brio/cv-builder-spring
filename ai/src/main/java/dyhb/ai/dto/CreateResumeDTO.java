package dyhb.ai.dto;


import lombok.Data;

import java.util.List;

@Data
public class CreateResumeDTO {
    private String applicationDescription;

    private List<EducationDTO> education;
    private List<JobDTO> jobs;
    private List<ProjectDTO> projects;
}
