package com.sasiri.jobapp.jobms.job.external;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class Review {
    Long id;
    String title;
    String description;
    Double rating;
}
