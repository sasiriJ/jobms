package com.sasiri.jobapp.jobms.job.dto;

import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.external.Company;
import lombok.AccessLevel;
import lombok.experimental.FieldNameConstants;
import lombok.Data;

@Data
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class JobWithCompanyDTO {
    Long id;
    String title;
    String description;
    String minSalary;
    String maxSalary;
    String location;
    Company company;
}
