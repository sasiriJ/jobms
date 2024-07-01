package com.sasiri.jobapp.jobms.job.client;

import com.sasiri.jobapp.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "companyms")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
     Company getCompany(@PathVariable Long id);
}
