package com.vados.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button_add;
    private Button button_settings;
    public static Notes notes = new Notes();
    public static FragmentManager fragmentManager;
    public static ItemFragmentNotes itemFragmentNotes = new ItemFragmentNotes();
    public static EnterNoteFragment enterNoteFragment = new EnterNoteFragment();

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

        fragmentManager =  getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,itemFragmentNotes)
                .commit();
    }

    public void ClickListener(){

        button_add.setOnClickListener(v -> {
            //открываем фрагмент с добавлением/изменением заметки
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,enterNoteFragment)
                    .addToBackStack("")
                    .commit();
        });

        button_settings.setOnClickListener(v -> {
            //открываем фрагмент с настройками
            Toast toast = Toast.makeText(getApplicationContext(),
                    String.valueOf(notes.getNotesSize()), Toast.LENGTH_SHORT);
            toast.show();
        });

    }


}