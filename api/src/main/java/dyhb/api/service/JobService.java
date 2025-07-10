package dyhb.api.service;

import dyhb.api.dto.CreateJobDTO;
import dyhb.api.database.models.Job;
import dyhb.api.database.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class JobService {

    @Autowired
    private final JobRepository jobRepository;

    public List<Job> getJobsByUserId(UUID userId) {
        return jobRepository.findByUserId(userId);
    }


    public Job addJob(CreateJobDTO jobDTO, UUID userId) {
        Job job = new Job();

        job.setTitle(jobDTO.getTitle());
        job.setCompany(jobDTO.getCompany());
        job.setDescription(jobDTO.getDescription());
        job.setStartDate(jobDTO.getStartDate());
        job.setEndDate(jobDTO.getEndDate());

        job.setUserId(userId);

        return jobRepository.save(job);

    }
}
