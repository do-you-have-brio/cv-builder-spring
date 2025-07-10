 package dyhb.api.database.models;

 import jakarta.persistence.*;
import lombok.Data;
 import org.hibernate.annotations.CreationTimestamp;
 import org.hibernate.annotations.UpdateTimestamp;

 import java.util.Date;
 import java.util.UUID;

@Data
@Entity
@Table(name = "jobs")
public class JobModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String company;
    private String description;

    private Date startDate;
    private Date endDate;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
