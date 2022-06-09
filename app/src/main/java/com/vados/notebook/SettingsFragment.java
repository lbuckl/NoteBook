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

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.Locale;

public class SettingsFragment extends Fragment {
    private TextView textView_lang;
    private TextView textView_them;
    private Spinner spinner_lang;
    private Spinner spinner_them;
    int lang; // Язык выбираемый селектором
    String systemLang;
    int them; //Тема выбираемая селектором
    String systemTheme;
    Context context;
    Resources.Theme theme;


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
        textView_them = view.findViewById(R.id.textView_themVal);;
        spinner_lang = view.findViewById(R.id.spinner_lang);
        spinner_them = view.findViewById(R.id.spinner_them);

        langList = view.getResources().getStringArray(R.array.Themes);
        themeList = view.getResources().getStringArray(R.array.Languiges);

        //sysTheme = view.getContext().getTheme(); // получаем текущую тему

        //Инициализируем и записываем язык в Вью
        systemLang = Locale.getDefault().getLanguage(); // получаем текущий язык
        setLangInValue(); // пишем значение в соответствующую позцицю

        //Инициализируем и записываем тему в Вью
        theme = view.getContext().getTheme();
        textView_them.setText(String.valueOf(MainActivity.themID));

        //textView_them.setText(systemTheme);
        //view.getContext().setTheme();
        //context.setTheme("Dark");

        lang = 0;
        them = 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Спинер изменения языка
        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = spinner_lang.getSelectedItemPosition();
                switch (lang){
                    case 1:
                        if (!systemLang.equals("en")){
                            setNewLocale("en");
                            setLangInValue();
                            restartActivity();
                        }
                        break;
                    case 2:
                        if (!systemLang.equals("ru")) {
                            setNewLocale("ru");
                            setLangInValue();
                            restartActivity();
                        }
                        break;
                    default:break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Спинер изменения Стиля
        /*spinner_them.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                them = spinner_them.getSelectedItemPosition();
                switch (them){
                    case 0:
                        sStyle = "Light";
                        break;
                    case 1:
                        sStyle = "Dark";
                        break;
                }
                textView_style.setText(sStyle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    // Сетаем текущий язык в текстовое поле
    void setLangInValue(){
        systemLang = Locale.getDefault().getLanguage(); // получаем текущий язык
        textView_lang.setText(systemLang);
    }

    void restartActivity(){
        Intent intent = new Intent();
        intent.setClass(context, context.getClass());
        requireActivity().recreate();
    }

    void setNewLocale(String locVal){
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

    //Возвращаем тему
    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case (0):
                return R.style.Theme_NoteBook;
            case (1):
                return R.style.Theme_Dark;
            default:
                return R.style.Theme_Red;
        }
    }

}