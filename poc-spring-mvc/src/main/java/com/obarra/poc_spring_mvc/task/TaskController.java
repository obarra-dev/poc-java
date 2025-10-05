package com.obarra.poc_spring_mvc.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController
{
    private final TaskRepository repository;

    public TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("tasks",repository.findAll());
        return "index";
    }

}
