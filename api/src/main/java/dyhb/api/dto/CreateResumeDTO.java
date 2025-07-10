package dyhb.api.dto;

import lombok.*;

import java.util.*;


@Getter
@Builder(toBuilder = true)
public class CreateResumeDTO {
    private UUID userId;
    private String name;
    private String link;
}
