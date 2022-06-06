package com.vados.notebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    /*public EnterNoteFragment() {
        // Required empty public constructor
    }*/

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Button button_apply = view.findViewById(R.id.button_apply);
        TextView textInput_NoteName = view.findViewById(R.id.textInput_noteName);
        TextView textInput_Note = view.findViewById(R.id.textInput_note);

        button_apply.setOnClickListener(v -> {
            String nName = textInput_NoteName.getText().toString();
            String nValue = textInput_Note.getText().toString();
            MainActivity.notes.addNewNote(nName, nValue);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,new ItemFragmentNotes())
                    .commit();
            //fragmentTransaction.remove(new EnterNoteFragment()).commit();
        });
    }
}