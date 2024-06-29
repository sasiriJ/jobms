package com.sasiri.jobapp.jobms.job;


import com.sasiri.jobapp.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("JobCreated Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteById(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job) {
        boolean updated = jobService.updateJob(id, job);
        if(updated) {
            return ResponseEntity.ok("Successfully updated job");
        }
        return ResponseEntity.notFound().build();

    }
}
