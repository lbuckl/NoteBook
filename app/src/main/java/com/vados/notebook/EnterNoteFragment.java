package com.vados.notebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.res.Configuration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EnterNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";*/

    // TODO: Rename and change types of parameters
    /*private String mParam1;
    private String mParam2;*/
    boolean replace = false;
    boolean isLandscape;
    int replaseID = 0;

    public EnterNoteFragment() {
    }

    public EnterNoteFragment(int id){
        this.replaseID = id;
    }

    // TODO: Rename and change types and number of parameters
    /*public static EnterNoteFragment newInstance(String param1, String param2) {
        EnterNoteFragment fragment = new EnterNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_note, container, false);

        //Проверяем на поворот экрана в горизонталь. true - значи повёрнут
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        Button button_apply = view.findViewById(R.id.button_apply);
        Button button_delete = view.findViewById(R.id.button_delete);
        TextView textInput_NoteName = view.findViewById(R.id.textInput_noteName);
        TextView textInput_Note = view.findViewById(R.id.textInput_note);

        if (replaseID > 0){
            textInput_NoteName.setText(MainActivity.notes.getNameForId(replaseID-1));
            textInput_Note.setText(MainActivity.notes.getNoteForId(replaseID-1));
            button_delete.setVisibility(View.VISIBLE);
            button_delete.setClickable(true);
        }
        else {
            button_delete.setVisibility(View.INVISIBLE);
            button_delete.setClickable(false);
        }

        button_apply.setOnClickListener(v -> {
            String nName = textInput_NoteName.getText().toString();
            String nValue = textInput_Note.getText().toString();

            if (replaseID > 0){
                MainActivity.notes.setNote(replaseID,nName,nValue);
            }else{
                MainActivity.notes.addNewNote(nName, nValue);
            }

            if (!isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,MainActivity.itemFragmentNotes)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,new EmptyFragment())
                        .replace(R.id.fragment_container, new ItemFragmentNotes())
                        .commit();
            }


        });

        button_delete.setOnClickListener(v -> {
            MainActivity.notes.deleteNoteForId(replaseID);

            if (!isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,MainActivity.itemFragmentNotes)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,new EmptyFragment())
                        .replace(R.id.fragment_container, new ItemFragmentNotes())
                        .commit();
            }
        });
    }
}