package dyhb.ai.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobDTO {
    private String title;
    private String company;
    private String description;
    private Date startDate;
    private Date endDate;
    private String userId;
}
