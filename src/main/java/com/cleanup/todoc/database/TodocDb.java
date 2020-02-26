package com.cleanup.todoc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;

import com.cleanup.todoc.dao.ProjectDAO;
import com.cleanup.todoc.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


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
                            .addCallback(roomCallback())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //--



    private static Callback roomCallback() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("projectId", 1L);
                contentValues.put("name", "C'est une tâche qui persiste");
                contentValues.put("creationTimestamp", 1581184803370L);
                db.insert("task", OnConflictStrategy.IGNORE, contentValues);

            }
        };
    }

}

