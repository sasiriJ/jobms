package com.sasiri.jobapp.jobms.job.mapper;

import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.dto.JobDTO;
import com.sasiri.jobapp.jobms.job.external.Company;
import com.sasiri.jobapp.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO toJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReview(reviews);
        return jobDTO;
    }
}
