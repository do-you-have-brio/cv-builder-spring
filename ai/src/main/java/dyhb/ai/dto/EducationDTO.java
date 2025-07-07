package dyhb.ai.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EducationDTO {
    private String degree;
    private String institution;
    private Date startDate;
    private Date endDate;
    private String userId;
}