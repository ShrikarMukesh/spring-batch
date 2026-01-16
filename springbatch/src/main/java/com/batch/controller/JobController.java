package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    Job secondJob;

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

        JobParameters  jobParameters = new JobParameters(params);

        if (jobName.equalsIgnoreCase("First Job")){
            jobLauncher.run(firstJob , jobParameters);
        }
        else if(jobName.equalsIgnoreCase("Second Job")){
            jobLauncher.run(secondJob , jobParameters);
        }

        return "Job Started";
    }

    @GetMapping("/start1/{jobName}")
    public String startJob1(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

        JobParameters  jobParameters = new JobParameters(params);

        if (jobName.equalsIgnoreCase("First Job")){
            jobLauncher.run(firstJob , jobParameters);
        }
        else if(jobName.equalsIgnoreCase("Second Job")){
            jobLauncher.run(secondJob , jobParameters);
        }

        return "Job Started";
    }
}
