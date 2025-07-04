package dyhb.cv.auth.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FormatResponse {

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "MM-dd-yyyy HH:mm:SSSZ",
        timezone = "GMT-3"
    )
    private Date timeStamp;

    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public FormatResponse(
        int httpStatusCode,
        HttpStatus httpStatus,
        String reason,
        String message
    ) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
