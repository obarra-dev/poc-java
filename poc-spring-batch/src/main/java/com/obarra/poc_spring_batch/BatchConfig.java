package com.obarra.poc_spring_batch;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig
{
  @Bean
  Job createJob(JobRepository jobRepository) {
    return new JobBuilder(jobRepository)
        .flow(createStep(jobRepository))
        .end().build();
  }

  @Bean
  Step createStep(JobRepository jobRepository) {
    return new StepBuilder(jobRepository)
        .<String, String> chunk(10)
        .allowStartIfComplete(true)
        .reader(new CustomReader())
        .processor(new CustomProcessor())
        .writer(new CustomWriter())
        .build();
  }
}
