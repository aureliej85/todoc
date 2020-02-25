package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;


@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM projects")
    List<Project> getProjects();

    @Insert
    void insertProjects(Project projects);

    @Update
    void updateProjects(Project projects);
}
