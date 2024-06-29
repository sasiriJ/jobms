package com.sasiri.jobapp.jobms.job.dto;

import com.sasiri.jobapp.jobms.job.Job;
import com.sasiri.jobapp.jobms.job.external.Company;
import lombok.AccessLevel;
import lombok.experimental.FieldNameConstants;
import lombok.Data;

@Data
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class JobWithCompanyDTO {
    Job job;
    Company company;
}
