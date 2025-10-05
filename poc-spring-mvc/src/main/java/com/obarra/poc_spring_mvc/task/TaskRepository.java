package com.obarra.poc_spring_mvc.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository
{
    private static final Logger log = LoggerFactory.getLogger(TaskRepository.class);

    private final List<Task> tasks = new ArrayList<>();

    private final ObjectMapper objectMapper;

    public TaskRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Task> findAll() {
        return tasks;
    }

    @PostConstruct
    private void init() {
        if(tasks.isEmpty()) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/tasks.json")) {
                Tasks tasks = objectMapper.readValue(inputStream, Tasks.class);
                log.info("Reading {} tasks from JSON data", tasks.tasks().size());
                this.tasks.addAll(tasks.tasks());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }

}
