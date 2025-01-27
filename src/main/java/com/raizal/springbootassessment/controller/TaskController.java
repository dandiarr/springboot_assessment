package com.raizal.springbootassessment.controller;

import com.raizal.springbootassessment.exception.ResourceNotFoundException;
import com.raizal.springbootassessment.model.Task;
import com.raizal.springbootassessment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() throws ResourceNotFoundException {

        List<Task> tasks = taskService.getAllTask();

            if (tasks.isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return ResponseEntity.ok(tasks);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() throws ResourceNotFoundException {

            List<Task> completedTask = taskService.findAllCompletedTask();

            if (completedTask.isEmpty()) {
                throw new ResourceNotFoundException();
            }
        return ResponseEntity.ok(completedTask);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() throws ResourceNotFoundException {
        List<Task> incompleteTask = taskService.findAllIncompleteTask();

        if (incompleteTask.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok(incompleteTask);
    }

        @PostMapping("/")
        public ResponseEntity<Task> createTask(@RequestBody Task task) {

            Task createdTask = taskService.createNewTask(task);

            return ResponseEntity.ok(createdTask);
        }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) throws ResourceNotFoundException {

        Task currentTask = taskService.findTaskById(id).map(foundTask-> {
            foundTask.setTask(task.getTask());
            foundTask.setCompleted(task.isCompleted());

            return taskService.updateTask(foundTask);
        }).orElseThrow(() -> new ResourceNotFoundException());

        return ResponseEntity.ok(currentTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask (@PathVariable Long id) throws ResourceNotFoundException {

        Task task = taskService.findTaskById(id).map(foundTask-> {
            taskService.deleteTask(id);

            return foundTask;
        }).orElseThrow(()->new ResourceNotFoundException());

        return ResponseEntity.ok(true);

    }
}
