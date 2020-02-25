package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDAO;
import com.cleanup.todoc.model.Task;

import java.util.List;


public class TaskRepository {
    private final TaskDAO taskDAO;

    public TaskRepository(TaskDAO taskDao) {
        taskDAO = taskDao;
    }

    public List<Task> getAllTasks() {
        return taskDAO.getTasks();
    }

    public void insertTask(Task task) {
        taskDAO.insertTask(task);
    }

    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }
}
