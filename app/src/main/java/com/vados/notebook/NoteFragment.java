package com.vados.notebook;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment {

    boolean isLandscape; //ориентация экрана
    int chooseID = 0;

    public NoteFragment(int id){
        this.chooseID = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textInput_Name = view.findViewById(R.id.textView_Name);
        TextView textInput_Note = view.findViewById(R.id.textView_Note);
        TextView textView_Date = view.findViewById(R.id.textView_Date);

        textInput_Name.setText(MainActivity.notes.getNameForId(chooseID-1));
        textInput_Note.setText("     " + MainActivity.notes.getNoteForId(chooseID-1));
        textView_Date.setText(MainActivity.notes.getDateForId(chooseID-1));
    }
}