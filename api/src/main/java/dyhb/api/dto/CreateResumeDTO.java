package dyhb.api.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateResumeDTO {

    private UUID userId;
    private String name;
    private String link;
}
