package com.sasiri.jobapp.jobms.job.mapper;

import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.dto.JobWithCompanyDTO;
import com.sasiri.jobapp.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO toJobWithCompanyDTO(Job job, Company company) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }
}
