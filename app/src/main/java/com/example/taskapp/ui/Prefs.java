package com.example.taskapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context){
        preferences=context.getSharedPreferences("setting",Context.MODE_PRIVATE);
    }
    public void saveBoardState(){
        preferences.edit().putBoolean("boardIsShown",true).apply();
    }
    public boolean isBoardShown(){
        return preferences.getBoolean("boardIsShown",true);
    }

    public void saveName(String name) {
        preferences.edit().putString("name",name).apply();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveImageUri(Uri selectedImageUri) {
        preferences.edit().putString("avatar",selectedImageUri.toString()).apply();
    }
    public Uri getImageUri() {
        return Uri.parse(preferences.getString("avatar", ""));
    }
}
