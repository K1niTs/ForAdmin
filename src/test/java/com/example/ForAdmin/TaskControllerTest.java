package com.example.ForAdmin;

import org.example.controller.TaskController;
import org.example.model.Task;
import org.example.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() {
        Task task = new Task(1, "Complete project documentation", 1);

        when(taskService.createTask(anyString(), anyInt())).thenReturn(task);

        Task createdTask = taskController.createTask(task);

        assertNotNull(createdTask);
        assertEquals("Complete project documentation", createdTask.getDescription());

        verify(taskService, times(1)).createTask("Complete project documentation", 1);
    }

    @Test
    void getAllTasks() {
        List<Task> tasks = Arrays.asList(
                new Task(1, "Complete project documentation", 1),
                new Task(2, "Prepare team presentation", 2)
        );

        when(taskService.getAllTasks()).thenReturn(tasks);

        List<Task> result = taskController.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Complete project documentation", result.get(0).getDescription());
    }

    @Test
    void getTaskById() {
        Task task = new Task(1, "Complete project documentation", 1);

        when(taskService.getTaskById(1)).thenReturn(Optional.of(task));

        Task result = taskController.getTaskById(1);

        assertNotNull(result);
        assertEquals("Complete project documentation", result.getDescription());
    }

    @Test
    void updateTask() {
        Task task = new Task(1, "Updated Task Description", 1);

        when(taskService.updateTask(eq(1), anyString(), anyInt())).thenReturn(Optional.of(task));

        Task result = taskController.updateTask(1, task);

        assertNotNull(result);
        assertEquals("Updated Task Description", result.getDescription());

        verify(taskService, times(1)).updateTask(1, "Updated Task Description", 1);
    }

    @Test
    void deleteTask() {
        when(taskService.deleteTask(1)).thenReturn(true);

        String response = taskController.deleteTask(1);

        assertEquals("Task deleted", response);
        verify(taskService, times(1)).deleteTask(1);
    }
}
