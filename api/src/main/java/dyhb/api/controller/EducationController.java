package dyhb.api.controller;

import dyhb.api.dto.CreateEducationDTO;
import dyhb.api.database.models.Education;
import dyhb.api.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/education")
@AllArgsConstructor
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Education>> getEducationByUserId(@PathVariable UUID userId) {
        List<Education> educationList = educationService.getEducationByUserId(userId);
        if (educationList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(educationList);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Education> addEducation(@RequestBody CreateEducationDTO educationDTO, @PathVariable UUID userId) {
        Education education = educationService.addEducation(educationDTO, userId);
        if (education == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(education);

    }

}
