package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDAO;
import com.cleanup.todoc.model.Project;

import java.util.List;


public class ProjectRepository {
    private final ProjectDAO projectDAO;

    public ProjectRepository(ProjectDAO projectDao) {
        projectDAO = projectDao;
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectDAO.getProjects();
    }


    public void insertProject(Project project){
         projectDAO.insertProject(project);
    }
}
