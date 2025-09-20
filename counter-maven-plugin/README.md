## to create a maven plugin project
mvn archetype:generate \
-DgroupId=com.obarra \
-DartifactId=counter-maven-plugin \
-DarchetypeGroupId=org.apache.maven.archetypes \
-DarchetypeArtifactId=maven-archetype-plugin

## To run the plugin followin the convention
mvn counter:dependency-counter

## To run the plugin
mvn com.obarra:counter-maven-plugin:1.0-SNAPSHOT:dependency-counter


## how to use
```xml
<plugins>
  <plugin>
    <groupId>com.obarra</groupId>
    <artifactId>counter-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
      <execution>
        <goals>
          <goal>dependency-counter</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <scope>compile</scope>
    </configuration>
  </plugin>
</plugins>
```
