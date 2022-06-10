package com.vados.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static  String AppTheme = "APP_THEME";
    private static  String NameSharedPreference = "LOGIN";

    private Button button_add;
    private Button button_settings;
    public static Notes notes = new Notes();
    public static FragmentManager fragmentManager;
    public static ItemFragmentNotes itemFragmentNotes = new ItemFragmentNotes();
    public static EnterNoteFragment enterNoteFragment;
    public static SettingsFragment settingsFragment;
    public static boolean isLandscape;
    public static int themID;
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getAppTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialization();
        ClickListener();
    }

    private void Initialization(){

        button_add = findViewById(R.id.button_add);
        button_settings = findViewById(R.id.button_settings);
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;

        fragmentManager =  getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,itemFragmentNotes)
                .commit();
    }

    public void ClickListener(){

        button_add.setOnClickListener(v -> {
            //открываем фрагмент с добавлением/изменением заметки

            enterNoteFragment = new EnterNoteFragment(0);
            if (isLandscape) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_note,enterNoteFragment)
                        .addToBackStack("EnterFragment")
                        .commit();
            }else{
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,enterNoteFragment)
                        .addToBackStack("EnterFragment")
                        .commit();
            }


        });

        button_settings.setOnClickListener(v -> {
            //открываем фрагмент с настройками
            settingsFragment = new SettingsFragment();
            if (isLandscape) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_note,settingsFragment)
                        .addToBackStack("EnterFragment")
                        .commit();
            }else{
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,settingsFragment)
                        .addToBackStack("EnterFragment")
                        .commit();
            }
        });

    }

    private int getAppTheme() {
        return codeStyleToStyleId();
    }

    //Возвращаем тему
    private int codeStyleToStyleId() {
        int codeStyle = getCodeStyle();
        switch (codeStyle) {
            case (1):
                return R.style.Theme_NoteBook;
            case (2):
                return R.style.Theme_Dark;
            case (3):
                return R.style.Theme_Red;
            default:
                return R.style.Theme_NoteBook;
        }
    }

    // Чтение настроек, параметр стиля/темы
    private int getCodeStyle(){
        int codeStyle;
        // Работаем через специальный класс сохранения и чтения настроек
        sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        codeStyle = sharedPref.getInt(AppTheme, 2);

        return codeStyle;
    }
}