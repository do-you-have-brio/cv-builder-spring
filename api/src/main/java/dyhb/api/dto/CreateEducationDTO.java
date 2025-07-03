package dyhb.api.dto;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class CreateEducationDTO {

    private UUID id;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private String description;

}
