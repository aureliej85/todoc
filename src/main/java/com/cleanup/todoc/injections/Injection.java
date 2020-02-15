package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.TodocDb;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Injection {

    public static ProjectRepository provideProjectDataSource(Context context) {
        TodocDb database = TodocDb.getInstance(context);
        return new ProjectRepository(database.projectDAO());
    }

    public static TaskRepository provideTaskDataSource(Context context) {
        TodocDb database = TodocDb.getInstance(context);
        return new TaskRepository(database.taskDAO());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ProjectRepository projectDataSource = provideProjectDataSource(context);
        TaskRepository taskDataSource = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataSource, taskDataSource, executor);
    }
}
