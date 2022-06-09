package com.vados.notebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsFragment extends Fragment {
    private TextView textView_lang;
    private TextView textView_them;
    private Spinner spinner_lang;
    private Spinner spinner_them;
    int lang; // Язык выбираемый селектором
    String sLang; // Язык получаемый селектором
    int theme; // Тема выбираемая селектором
    String sTheme; // Язык получаемый селектором

    String[] langList;
    String[] themeList;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //langList = view.getResources().Theme;
        langList = view.getResources().getStringArray(R.array.Themes);
        themeList = view.getResources().getStringArray(R.array.Languiges);

        lang = 0;
        theme = 0;
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
                    case 0:
                        sLang = langList[0];
                        break;
                    case 1:
                        sLang = langList[1];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lang = 0;
                textView_lang.setText(sLang);
            }
        });
    }
}