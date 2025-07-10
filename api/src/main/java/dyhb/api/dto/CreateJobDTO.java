package dyhb.api.dto;

import lombok.*;

import java.util.*;


@Getter
@Builder(toBuilder = true)
public class CreateJobDTO {
    private UUID id;
    private  String title;
    private String company;
    private String description;
    private Date startDate;
    private Date endDate;
}
