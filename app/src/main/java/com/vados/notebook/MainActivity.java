package com.vados.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.vados.notebook.main.ItemFragmentNotes;
import com.vados.notebook.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private static String AppTheme = "APP_THEME";
    private static String NameSharedPreference = "LOGIN";
    public static Notes notes = new Notes();
    public static FragmentManager fragmentManager;
    public static ItemFragmentNotes itemFragmentNotes = new ItemFragmentNotes();
    public static MainFragment mainFragment = new MainFragment();
    public static EnterNoteFragment enterNoteFragment;
    public static SettingsFragment settingsFragment;
    public static AboutAppFragment aboutAppFragment;
    public static boolean isLandscape;
    public static int themID;
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getAppTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialization();
        ClickListener();
    }

    private void Initialization(){
        //верхнее меню
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //боковое меню
        initToolbar();
        runToolbar();

        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;

        fragmentManager =  getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,mainFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.aboutApp:
                clearBackStack();
                aboutAppFragment = new AboutAppFragment();
                if (isLandscape) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container_note,aboutAppFragment)
                            .addToBackStack("Settings")
                            .commit();
                }else{
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container,aboutAppFragment)
                            .addToBackStack("Settings")
                            .commit();
                }
                    return true;
            case R.id.app_bar_search:
                    Toast toast1 = Toast.makeText(this, "Найти", Toast.LENGTH_LONG);
                    toast1.show();
                    return true;

            case R.id.settings:
                clearBackStack();
                settingsFragment = new SettingsFragment();
                if (isLandscape) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container_note,settingsFragment)
                            .addToBackStack("Settings")
                            .commit();
                }else{
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container,settingsFragment)
                            .addToBackStack("Settings")
                            .commit();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Находим Drawer_layout
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.apply,
                R.string.delete);
        drawer.addDrawerListener(toogle);
        toogle.syncState();
    }

    private void runToolbar(){
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.drawer_about:
                        drawer.close();
                        clearBackStack();
                        aboutAppFragment = new AboutAppFragment();
                        if (isLandscape) {
                            fragmentManager
                                    .beginTransaction()
                                    .add(R.id.fragment_container_note,aboutAppFragment)
                                    .addToBackStack("Settings")
                                    .commit();
                        }else{
                            drawer.close();
                            clearBackStack();
                            fragmentManager
                                    .beginTransaction()
                                    .add(R.id.fragment_container,aboutAppFragment)
                                    .addToBackStack("Settings")
                                    .commit();
                        }
                        return true;
                    case R.id.drawer_settings:
                        drawer.close();
                        clearBackStack();
                        settingsFragment = new SettingsFragment();
                        if (isLandscape) {
                            fragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container_note,settingsFragment)
                                    .addToBackStack("Settings")
                                    .commit();
                        }else{
                            fragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container,settingsFragment)
                                    .addToBackStack("Settings")
                                    .commit();
                        }
                        return true;
                    case R.id.drawer_exit:
                        finish();
                }
                return false;
            }
        });
    }

    public void ClickListener(){

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
        themID = codeStyle;
        return codeStyle;
    }

    private static void clearBackStack(){
        //очищаем бэкстэк
        if (fragmentManager.getBackStackEntryCount() > 0){
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}