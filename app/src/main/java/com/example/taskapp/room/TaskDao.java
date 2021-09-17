 package com.example.taskapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.taskapp.ui.home.Task;

import java.util.List;

@Dao
public  interface TaskDao {
    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    abstract void insert(Task task);
}
