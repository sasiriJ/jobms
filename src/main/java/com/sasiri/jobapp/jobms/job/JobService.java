package com.sasiri.jobapp.jobms.job;

import com.sasiri.jobapp.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job findById(Long id);
    boolean deleteById(Long id);
    boolean updateJob(Long id, Job job);
}
