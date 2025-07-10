package dyhb.api.service;

import dyhb.api.dto.CreateEducationDTO;
import dyhb.api.database.models.Education;
import dyhb.api.database.repository.EducationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EducationService {

    @Autowired
    private final EducationRepository educationRepository;

    public List<Education>getEducationByUserId(UUID userId) {
        return educationRepository.findByUserId(userId);

    }

    public Education addEducation(CreateEducationDTO educationDTO, UUID userId) {
        Education education = new Education();

        education.setDegree(educationDTO.getDegree());
        education.setInstitution(educationDTO.getInstitution());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());

        education.setUserId(userId);

        return educationRepository.save(education);
    }
}
