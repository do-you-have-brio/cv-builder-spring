package dyhb.api.service;

import dyhb.api.dto.CreateResumeDTO;
import dyhb.api.entities.Resume;
import dyhb.api.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResumeService {

    @Autowired
    private final ResumeRepository resumeRepository;

    public List<Resume> getResumeByUserId(UUID userId) {
        return resumeRepository.findByUserId(userId);
    }

    public Resume addResume(CreateResumeDTO createResumeDTO, UUID userId) {
        Resume resume = new Resume();

        resume.setName(createResumeDTO.getName());
        resume.setLink(createResumeDTO.getLink());

        resume.setUserId(userId);

        return resumeRepository.save(resume);
    }

}
