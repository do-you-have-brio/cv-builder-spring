package dyhb.api.database.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "resumes")
public class ResumeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String link;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

}
