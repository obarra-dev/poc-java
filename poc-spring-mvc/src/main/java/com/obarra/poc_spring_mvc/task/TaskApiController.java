package com.obarra.poc_spring_mvc.task;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskApiController
{
    private final TaskRepository repository;

    public TaskApiController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Task> findAll() {
        return repository.findAll();
    }
}
