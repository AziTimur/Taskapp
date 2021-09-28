package com.example.taskapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("boardIsShown", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("boardIsShown", true);
    }

    public void saveName(String name) {
        preferences.edit().putString("name", name).apply();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveImageUri(Uri selectedImageUri) {
        preferences.edit().putString("avatar", selectedImageUri.toString()).apply();
    }

    public Uri getImageUri() {
        return Uri.parse(preferences.getString("avatar", ""));
    }

    public void clearSetting(){
        preferences.edit().clear().clear().apply();
    }

    public void saveEmail(String email) {
        preferences.edit().putString("email",email).apply();
    }
    public String getEmail() {
        return preferences.getString("email", "");
    }

    public void savePhone(String phone) {
        preferences.edit().putString("phone",phone).apply();
    }
    public String getPhone() {
        return preferences.getString("phone", "");
    }

    public void saveAddress(String address) {
        preferences.edit().putString("address",address).apply();
    }
    public String getAddress() {
        return preferences.getString("address", "");
    }

    public void saveBirthDay(String birthDay) {
        preferences.edit().putString("birthDay",birthDay).apply();
    }
    public String getBirthDay() {
        return preferences.getString("birthDay", "");
    }

}

