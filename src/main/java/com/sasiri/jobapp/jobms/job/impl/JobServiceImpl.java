package com.sasiri.jobapp.jobms.job.impl;


import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.JobRepository;
import com.sasiri.jobapp.jobms.job.JobService;
import com.sasiri.jobapp.jobms.job.client.CompanyClient;
import com.sasiri.jobapp.jobms.job.client.ReviewClient;
import com.sasiri.jobapp.jobms.job.dto.JobDTO;
import com.sasiri.jobapp.jobms.job.external.Company;
import com.sasiri.jobapp.jobms.job.external.Review;
import com.sasiri.jobapp.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    @CircuitBreaker(name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public List<String> companyBreakerFallback(Exception e) {
        return List.of("Dummy","Company Microservice is down");
    }

    private JobDTO convertToDto(Job job) {
        /*Company company = restTemplate.
                getForObject("http://companyms:8081/companies/" + job.getCompanyId(),
                        Company.class);*/
        Company company = companyClient.getCompany(job.getCompanyId());

        /*ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://reviewms:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                });*/

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

//        List<Review> reviews = reviewResponse.getBody();

        JobDTO jobDTO = JobMapper.toJobWithCompanyDTO(job, company, reviews);

        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);

    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;

    }

}
