package com.example.taskapp.ui.home;

import android.provider.ContactsContract;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String createAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Task(String title, String createAt) {
        this.title = title;
        this.createAt = createAt;
    }
}
