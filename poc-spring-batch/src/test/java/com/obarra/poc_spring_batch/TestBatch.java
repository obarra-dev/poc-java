package com.obarra.poc_spring_batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = BatchConfig.class)
class TestBatch
{
  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  void contextLoads() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    ExitStatus exitStatus = jobExecution.getExitStatus();
    Assertions.assertEquals(ExitStatus.COMPLETED, exitStatus);
  }
}
