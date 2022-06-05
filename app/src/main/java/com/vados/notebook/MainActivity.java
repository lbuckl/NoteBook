package com.vados.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button_add;
    private Button button_settings;
    public static Notes notes = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialization();
        ClickListener();
    }

    private void Initialization(){
        button_add = findViewById(R.id.button_add);
        button_settings = findViewById(R.id.button_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new ItemFragmentNotes())
                .commit();
    }

    public void ClickListener(){
        button_add.setOnClickListener(v -> {
            //открываем фрагмент с добавлением/изменением заметки

        });

        button_settings.setOnClickListener(v -> {
            //открываем фрагмент с настройками
            Toast toast = Toast.makeText(getApplicationContext(),
                    notes.getNameForId(1), Toast.LENGTH_SHORT);
            toast.show();
        });
    }

}