package com.cleanup.todoc.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.ui.ProjectAndTaskViewModel;

import java.util.concurrent.Executor;


public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ProjectRepository mProjectDataSource;
    private final TaskRepository mTaskDataSource;
    private final Executor mExecutor;

    public ViewModelFactory(ProjectRepository projectDataSource, TaskRepository taskDataSource, Executor executor) {
        mProjectDataSource = projectDataSource;
        mTaskDataSource = taskDataSource;
        mExecutor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProjectAndTaskViewModel.class)) {
            return (T) new ProjectAndTaskViewModel(mProjectDataSource, mTaskDataSource, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
