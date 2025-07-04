package dyhb.api.dto;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class CreateJobDTO {

    private UUID id;
    private  String title;
    private String company;
    private String description;

    private Date startDate;
    private Date endDate;

}
