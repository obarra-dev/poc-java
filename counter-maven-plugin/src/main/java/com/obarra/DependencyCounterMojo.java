package com.obarra;

import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "dependency-counter", defaultPhase = LifecyclePhase.COMPILE)
public class DependencyCounterMojo
    extends AbstractMojo
{
  @Parameter(defaultValue = "${project}", required = true, readonly = true)
  MavenProject project;

  @Parameter(property = "scope")
  String scope;

  public void execute() {
    List<Dependency> dependencies = project.getDependencies();
    if (scope == null || scope.isEmpty()) {
      long numDependencies = dependencies.size();
      getLog().info("Number of dependencies: " + numDependencies);
      return;
    }

    long numDependencies = dependencies.stream()
        .filter(d -> (d.getScope() == null && "compile".equals(scope) || scope.equals(d.getScope())))
        .count();
    getLog().info("Number of dependencies with scope '" + scope + "': " + numDependencies);
  }
}
