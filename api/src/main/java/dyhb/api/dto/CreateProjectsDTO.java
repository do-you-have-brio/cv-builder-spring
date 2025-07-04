package dyhb.api.dto;


import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateProjectsDTO {

    private UUID id;
    private String name;
    private String description;
}
