package com.example.taskapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskapp.room.AppDataBase;
import com.example.taskapp.ui.Prefs;

public class App extends Application {

    private static AppDataBase appDataBase;
    private static Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, "mydb")
                .allowMainThreadQueries().build();
    }

    public static Prefs getPrefs() {
        return prefs;
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;

    }
}
