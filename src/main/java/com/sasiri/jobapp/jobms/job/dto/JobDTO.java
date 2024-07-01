package com.sasiri.jobapp.jobms.job.dto;

import com.sasiri.jobapp.jobms.job.external.Company;
import com.sasiri.jobapp.jobms.job.external.Review;
import lombok.AccessLevel;
import lombok.experimental.FieldNameConstants;
import lombok.Data;

import java.util.List;

@Data
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class JobDTO {
    Long id;
    String title;
    String description;
    String minSalary;
    String maxSalary;
    String location;
    Company company;
    List<Review> review;
}
