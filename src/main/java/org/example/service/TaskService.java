package org.example.service;


import org.example.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>() {{
        add(new Task(1, "Complete project documentation", 1));
        add(new Task(2, "Prepare team presentation", 2));
        add(new Task(3, "Fix critical bug in production", 3));
    }};

    private int currentId = 4;

    public Task createTask(String description, int userId) {
        Task task = new Task(currentId++, description, userId);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Optional<Task> getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    public Optional<Task> updateTask(int id, String description, int userId) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                task.setUserId(userId);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
