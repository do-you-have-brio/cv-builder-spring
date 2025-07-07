package dyhb.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiResponse {
    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;
    private String responseId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Candidate {
        private Content content;
        private String finishReason;
        private Double avgLogprobs;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private List<Part> parts;
        private String role;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Part {
        private String text;
        // Add other fields if needed
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsageMetadata {
        @JsonProperty("promptTokenCount")
        private Integer promptTokenCount;
        @JsonProperty("candidatesTokenCount")
        private Integer candidatesTokenCount;
        @JsonProperty("totalTokenCount")
        private Integer totalTokenCount;
        private List<TokenDetails> promptTokensDetails;
        private List<TokenDetails> candidatesTokensDetails;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenDetails {
        private String modality;
        private Integer tokenCount;
    }
}