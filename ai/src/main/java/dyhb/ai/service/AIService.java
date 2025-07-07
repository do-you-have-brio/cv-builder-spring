package dyhb.ai.service;

import dyhb.ai.config.AICredentialsConfig;
import dyhb.ai.dto.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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

        GeminiResponse response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();

        if (response == null ||
                response.getCandidates() == null ||
                response.getCandidates().isEmpty()) {
            throw new RuntimeException("No response from Gemini API");
        }

        return response.getCandidates().getFirst()
                .getContent().getParts().getFirst()
                .getText();
    }

    private String buildPrompt(CreateResumeDTO request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generate a professional resume in Markdown (MD) format based on the following details. ");
        sb.append("Use proper Markdown syntax for headings, lists, and formatting. ");
        sb.append("The output should be ready to save as a .md file:\n\n");

        sb.append("Application description: ").append(request.getApplicationDescription()).append("\n\n");

        sb.append("Profile details:\n");
        if (request.getProfile() != null) {
            sb.append("Name: ").append(request.getProfile().getName()).append("\n")
                    .append("Phone: ").append(request.getProfile().getPhone()).append("\n")
                    .append("Email: ").append(request.getProfile().getEmail()).append("\n");

            if (request.getProfile().getLinkedinUrl() != null) {
                sb.append("LinkedIn: ").append(request.getProfile().getLinkedinUrl()).append("\n");
            }
            if (request.getProfile().getPortfolioUrl() != null) {
                sb.append("Portfolio: ").append(request.getProfile().getPortfolioUrl()).append("\n");
            }
            sb.append("\n");
        }

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

        sb.append("\nFormat the output in proper Markdown syntax with:");
        sb.append("\n- Headings using ## or ###");
        sb.append("\n- Bullet points using * or -");
        sb.append("\n- Bold text using **");
        sb.append("\n- Proper section separation");
        sb.append("\n- No surrounding markdown code blocks (```)");
        return sb.toString();
    }

}
