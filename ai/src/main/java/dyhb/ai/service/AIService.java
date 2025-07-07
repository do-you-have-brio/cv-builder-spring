package dyhb.ai.service;

import dyhb.ai.config.AICredentialsConfig;
import dyhb.ai.dto.CreateResumeDTO;
import dyhb.ai.dto.EducationDTO;
import dyhb.ai.dto.JobDTO;
import dyhb.ai.dto.ProjectDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AIService {

    private WebClient webClient;
    private final AICredentialsConfig credentialsConfig;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(credentialsConfig.getUrl())
                .defaultHeader("X-goog-api-key", credentialsConfig.getKey())
                .build();
    }

    public String generateResumePdf(CreateResumeDTO request) {
        String prompt = buildPrompt(request);

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String aiResponse = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return aiResponse;
    }

    private String buildPrompt(CreateResumeDTO request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a professional resume based on the following details:\n\n");
        sb.append("Application description: ").append(request.getApplicationDescription()).append("\n\n");

        sb.append("Education:\n");
        for (EducationDTO edu : request.getEducation()) {
            sb.append("- ").append(edu.getDegree()).append(" at ").append(edu.getInstitution())
                    .append(" (").append(edu.getStartDate()).append(" to ").append(edu.getEndDate()).append(")\n");
        }

        sb.append("\nWork Experience:\n");
        for (JobDTO job : request.getJobs()) {
            sb.append("- ").append(job.getTitle()).append(" at ").append(job.getCompany())
                    .append(" (").append(job.getStartDate()).append(" to ").append(job.getEndDate()).append(")\n")
                    .append("  ").append(job.getDescription()).append("\n");
        }

        sb.append("\nProjects:\n");
        for (ProjectDTO project : request.getProjects()) {
            sb.append("- ").append(project.getName()).append(": ").append(project.getDescription()).append("\n");
        }

        sb.append("\nFormat the output in a structured, resume-like format.");
        return sb.toString();
    }

}
