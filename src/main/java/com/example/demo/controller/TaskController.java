package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> list() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> getOne(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task edit(@PathVariable Long id, @RequestBody Task task) {
        Task oldTask = taskRepository.findById(id).get();

        oldTask.date = task.date;
        oldTask.description = task.description;
        oldTask.done = task.done;

        return taskRepository.save(oldTask);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id) {
        taskRepository.markAsDone(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
