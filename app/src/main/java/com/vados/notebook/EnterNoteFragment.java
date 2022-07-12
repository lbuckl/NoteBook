package com.vados.notebook;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    Date selectDate = null;
    Calendar gcalendar = new GregorianCalendar();
    FormatDate formatDate = new FormatDate();

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
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_note, container, false);

        //Проверяем на поворот экрана в горизонталь. true - значи повёрнут
        isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        //gcalendar.add(Calendar.HOUR,5);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        Button button_apply = view.findViewById(R.id.button_apply);
        Button button_delete = view.findViewById(R.id.button_delete);
        Button button_selectDate = view.findViewById(R.id.button_selectDate);
        TextView textInput_NoteName = view.findViewById(R.id.textInput_noteName);
        TextView textInput_Note = view.findViewById(R.id.textInput_note);
        TextView textView_Date = view.findViewById(R.id.textView_date);

        if (replaseID > 0){
            textInput_NoteName.setText(MainActivity.notes.getNameForId(replaseID-1));
            textInput_Note.setText(MainActivity.notes.getNoteForId(replaseID-1));
            textView_Date.setText(formatDate.getCustomStringDate(MainActivity.notes.getDateForId(replaseID-1)));
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
                if (selectDate != null) MainActivity.notes.setNote(replaseID,nName,nValue,selectDate);
                else MainActivity.notes.setNote(replaseID,nName,nValue);
            }else{
                MainActivity.notes.addNewNote(nName, nValue);
            }

            selectDate = null;

            if (!isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,MainActivity.itemFragmentNotes)
                        .addToBackStack("EnteredNote")
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,new EmptyFragment())
                        .replace(R.id.fragment_container, new ItemFragmentNotes())
                        .addToBackStack("EnteredNote")
                        .commit();
            }
        });

        button_delete.setOnClickListener(v -> {
            MainActivity.notes.deleteNoteForId(replaseID);

            if (!isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,MainActivity.itemFragmentNotes)
                        .addToBackStack("EnteredNote")
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,new EmptyFragment())
                        .replace(R.id.fragment_container, new ItemFragmentNotes())
                        .addToBackStack("EnteredNote")
                        .commit();
            }
        });

        button_selectDate.setOnClickListener(v -> {
            int YEAR = gcalendar.getWeekYear();
            int MONTH = gcalendar.DAY_OF_MONTH+1;
            int DAY = gcalendar.DAY_OF_WEEK_IN_MONTH;
            gcalendar.set(YEAR,MONTH,DAY);

            Context context = view.getContext();
            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (view1, year, month, dayOfMonth) -> {
                        gcalendar.set(year,month,dayOfMonth);
                        selectDate = gcalendar.getTime();
                        textView_Date.setText(formatDate.getCustomStringDate(selectDate));
                    },YEAR,MONTH,DAY);
            datePickerDialog.show();
        });
    }
}