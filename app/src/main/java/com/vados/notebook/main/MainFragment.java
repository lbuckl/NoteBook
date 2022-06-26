package com.vados.notebook.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.vados.notebook.EnterNoteFragment;
import com.vados.notebook.MainActivity;
import com.vados.notebook.R;

public class MainFragment extends Fragment {

    ImageButton imageButton_add;

    public MainFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        imageButton_add = view.findViewById(R.id.imageButton_add);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageButton_add.setOnClickListener(v -> {
            //открываем фрагмент с добавлением/изменением заметки
            MainActivity.enterNoteFragment = new EnterNoteFragment(0);
            if (MainActivity.isLandscape) {
                MainActivity.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_note,MainActivity.enterNoteFragment)
                        .commit();
            }else{
                MainActivity.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,MainActivity.enterNoteFragment)
                        .commit();
            }
        });
    }
}