package com.example.DZ53.controllers;



import com.example.DZ53.model.Status;
import com.example.DZ53.model.Task;
import com.example.DZ53.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
@AllArgsConstructor
public class TaskController {
    private TaskRepository taskRepository;

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return (Task) taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        Task current = taskRepository.findById(id).orElse(null);
        if (current != null){
            int NumStatus = current.getStatus().ordinal();
            if (current.getStatus() != Status.COMPLETED){
                NumStatus++;
                current.setStatus(Status.values()[NumStatus]);
            }
        }
        return taskRepository.save(current);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);

    }

    @GetMapping("/status/{status}")
    public <TaskStatus> List<Task> getTasksByStatus(@PathVariable TaskStatus status) { return taskRepository.findByStatus((Status) status);
    }
}
