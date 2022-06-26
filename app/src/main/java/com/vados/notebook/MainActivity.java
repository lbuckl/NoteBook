package com.vados.notebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Для хранения значений сохраняемой темы и языка
    private static final String NameSharedPreference = "LOGIN";
    private static final String AppTheme = "APP_THEME";
    private static final String AppLang = "APP_LANG";
    //Для сохранения класса Notes
    private static final String NameSharedClass= "NOTE_ITEMS";
    private static final String AppClassNote = "APP_CLASS_NOTE";


    //public static Notes notes = new Notes();
    public static Notes notes;
    public static FragmentManager fragmentManager;
    public static ItemFragmentNotes itemFragmentNotes = new ItemFragmentNotes();
    public static MainFragment mainFragment = new MainFragment();
    public static EnterNoteFragment enterNoteFragment;
    public static SettingsFragment settingsFragment;
    public static AboutAppFragment aboutAppFragment;
    public static boolean isLandscape;
    public static int themID;
    String locate;
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPrefClass = null;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getAppTheme());
        setNewLocale();
        super.onCreate(savedInstanceState);
        sharedPrefClass = getPreferences(MODE_PRIVATE);

        String savedNote = sharedPrefClass.getString(AppClassNote, null);
        if (savedNote == null) {
            notes = new Notes();
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        } else {
            notes = new GsonBuilder().create().fromJson(savedNote,
                    Notes.class);
        }

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
                //clearBackStack();
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
                                    .commit();
                        }else{
                            fragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container,settingsFragment)
                                    .commit();
                        }
                        return true;
                    case R.id.drawer_exit:
                        Resources resources = getBaseContext().getResources();
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertBuilder
                                .setTitle(resources.getString(R.string.Exit))
                                .setPositiveButton(resources.getString(R.string.Yes),
                                        (dialog, which) -> {
                                            finishAffinity();
                                        })
                                .setNegativeButton(resources.getString(R.string.No),
                                        (dialog, which) -> {
                                            drawer.close();
                                        })
                                .show();

                        return true;
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

    public void setNewLocale(){
        Context context = getBaseContext();
        sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        locate = sharedPref.getString(AppLang, "en");
        Locale locale = new Locale(locate);
        Locale.setDefault(locale);

        //Изменяем язык конфигурации джава
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        context.createConfigurationContext(configuration);

        //Изменяем язык ресурсов
        Resources resources = context.getResources();
        Configuration configuration1 = resources.getConfiguration();
        configuration1.setLocale(locale);
        resources.updateConfiguration(configuration1, resources.getDisplayMetrics());
    }
}