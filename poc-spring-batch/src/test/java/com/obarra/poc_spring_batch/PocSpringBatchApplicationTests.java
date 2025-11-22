package com.obarra.poc_spring_batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = BatchConfig.class)
class PocSpringBatchApplicationTests {

  @Autowired
  // this is deprecated but I don't know the alternative yet
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  void contextLoads() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}
