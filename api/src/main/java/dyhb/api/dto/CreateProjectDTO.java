package dyhb.api.dto;


import lombok.*;

import java.util.*;

@Getter
@Builder(toBuilder = true)
public class CreateProjectDTO {
    private UUID id;
    private String name;
    private String description;
}
