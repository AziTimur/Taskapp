package com.example.taskapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskapp.room.AppDataBase;

public class App extends Application {
    private  static AppDataBase appDataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase= Room.databaseBuilder(this,AppDataBase.class,"mydb")
                .allowMainThreadQueries().build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;

    }
}
