package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;


public class ProjectAndTaskViewModel extends ViewModel {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final Executor executor;

    @Nullable
    private LiveData<List<Project>> projects;

    public ProjectAndTaskViewModel(ProjectRepository projectDataSource, TaskRepository taskDataSource, Executor executor) {
        projectRepository = projectDataSource;
        taskRepository = taskDataSource;
        this.executor = executor;
    }

    public void init() {
        if (projects == null)
            projects = projectRepository.getAllProjects();
    }

    @Nullable
    public LiveData<List<Project>> getAllProjects() {
        return projects;
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void addTask(Task task) {
        executor.execute(() -> {
            taskRepository.insertTask(task);
        });
    }


    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskRepository.deleteTask(task);
        });
    }
}
