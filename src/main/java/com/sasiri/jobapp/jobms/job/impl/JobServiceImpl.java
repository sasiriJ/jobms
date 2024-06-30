package com.sasiri.jobapp.jobms.job.impl;


import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.JobRepository;
import com.sasiri.jobapp.jobms.job.JobService;
import com.sasiri.jobapp.jobms.job.dto.JobWithCompanyDTO;
import com.sasiri.jobapp.jobms.job.external.Company;
import com.sasiri.jobapp.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    private JobWithCompanyDTO convertToDto(Job job) {
        Company company = restTemplate.
                getForObject("http://companyms:8081/companies/" + job.getCompanyId(),
                        Company.class);

        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.toJobWithCompanyDTO(job, company);

        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO findById(Long id) {
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
