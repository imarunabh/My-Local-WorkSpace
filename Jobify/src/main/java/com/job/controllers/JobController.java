package com.job.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/create")
    public String createJob(@RequestBody String jobDetails) {
        return jobService.createJob(jobDetails);
    }

    @PreAuthorize("hasAnyRole('EMPLOYER', 'JOB_SEEKER')")
    @GetMapping("/list")
    public List<String> listJobs() {
        return jobService.getAllJobs();
    }
}
