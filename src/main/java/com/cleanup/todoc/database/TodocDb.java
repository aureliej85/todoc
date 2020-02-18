package com.cleanup.todoc.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.cleanup.todoc.R;
import com.cleanup.todoc.dao.ProjectDAO;
import com.cleanup.todoc.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;


@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDb extends RoomDatabase {

    //singleton
    public static volatile TodocDb INSTANCE;

    public abstract ProjectDAO projectDAO();
    public abstract TaskDAO taskDAO();


    //instance

    public static TodocDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDb.class,
                            "TodocDb.db")
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //--


    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            ContentValues contentValues = new ContentValues();
            contentValues.put("projectId", 1L);
            contentValues.put("name", "C'est une tâche qui persiste");
            contentValues.put("creationTimestamp", 1581184803370L);
            db.insert("tasks", OnConflictStrategy.IGNORE, contentValues);
        }
    };



}

