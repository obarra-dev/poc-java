package com.obarra;

import java.io.File;

import org.apache.maven.it.Verifier;
import org.junit.Test;

public class DependencyCounterMojoIT
{
  @Test
  public void stagingDeploy() throws Exception {
    File resourcesDirectory = new File("src/test/resources/app");

    Verifier verifier  = new Verifier(resourcesDirectory.getAbsolutePath());
    verifier.executeGoal("counter:dependency-counter");
    verifier.verifyTextInLog("Number of dependencies with scope 'compile': 2");
  }
}