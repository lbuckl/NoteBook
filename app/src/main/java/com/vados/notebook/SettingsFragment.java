package com.vados.notebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.Locale;

public class SettingsFragment extends Fragment {
    private static final String AppTheme = "APP_THEME";
    private static final String NameSharedPreference = "LOGIN";

    private TextView textView_lang;
    private TextView textView_them;
    private Spinner spinner_lang;
    private Spinner spinner_them;
    private Button button_apply;
    int lang; // Язык выбираемый селектором
    String systemLang;
    String chooseLang;
    int them; //Тема выбираемая селектором
    String systemTheme;
    Context context;
    Resources.Theme theme;
    private boolean isLandscape;

    String[] langList;
    String[] themeList;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initialization(view);
        return view;
    }

    private void initialization(View view){
        textView_lang = view.findViewById(R.id.textView_langVal);
        textView_them = view.findViewById(R.id.textView_themVal);
        spinner_lang = view.findViewById(R.id.spinner_lang);
        spinner_them = view.findViewById(R.id.spinner_them);
        button_apply = view.findViewById(R.id.button_applySettings);

        langList = view.getResources().getStringArray(R.array.Languiges);
        themeList = view.getResources().getStringArray(R.array.Themes);

        //Инициализируем и записываем язык в Вью
        systemLang = Locale.getDefault().getLanguage(); // получаем текущий язык
        setLangInValue(); // пишем значение в соответствующую позцицю

        //Инициализируем и записываем тему в Вью
        setThemeInValue(MainActivity.themID);

        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        lang = 0;
        them = MainActivity.themID;
        chooseLang = systemLang;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        //Спинер изменения языка
        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = spinner_lang.getSelectedItemPosition();
                switch (lang){
                    case 1:
                        chooseLang = "en";
                        break;
                    case 2:
                        chooseLang = "ru";
                        break;
                    default:break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Спинер изменения Стиля
        spinner_them.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int themID = spinner_them.getSelectedItemPosition();
                switch (themID){
                    case 1:
                        them = 1;
                        break;
                    case 2:
                        them = 2;
                        break;
                    case 3:
                        them = 3;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button_apply.setOnClickListener(v -> {
            boolean isChose = false;
            if (them != MainActivity.themID){
                setAppTheme(them);
                isChose = true;
            }

            if (!chooseLang.equals(systemLang)){
                setNewLocale(chooseLang);
                setLangInValue();
                isChose = true;
            }

            if (isChose) restartActivity();
            else {
                if (!isLandscape){
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,MainActivity.itemFragmentNotes)
                            .addToBackStack("ApplySettings")
                            .commit();
                }else{
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_note,new EmptyFragment())
                            .replace(R.id.fragment_container, new ItemFragmentNotes())
                            .addToBackStack("ApplySettings")
                            .commit();
                }
            }
        });
    }

    // Сетаем текущий язык в текстовое поле
    void setLangInValue(){
        int val = 0;
        systemLang = Locale.getDefault().getLanguage(); // получаем текущий язык
        if (systemLang.equals("en")) val = 1;
        if (systemLang.equals("ru")) val = 2;
        textView_lang.setText(langList[val]);
    }

    void setThemeInValue(int themeID){
        textView_them.setText(themeList[themeID]);
    }

    void restartActivity(){
        Intent intent = new Intent();
        intent.setClass(context, context.getClass());
        requireActivity().recreate();
    }

    public void setNewLocale(String locVal){
        Locale locale = new Locale(locVal);
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

    // Сохранение настроек стиля
    private void setAppTheme(int codeStyle) {
        MainActivity.sharedPref = context.getSharedPreferences(NameSharedPreference,context.MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        MainActivity.editor = MainActivity.sharedPref.edit();
        MainActivity.editor.putInt(AppTheme, codeStyle);
        MainActivity.editor.apply();
    }

}