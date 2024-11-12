package com.job.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final List<String> jobList = new ArrayList<>();

    public String createJob(String jobDetails) {
        jobList.add(jobDetails);
        return "Job created successfully!";
    }

    public List<String> getAllJobs() {
        return jobList;
    }
}
