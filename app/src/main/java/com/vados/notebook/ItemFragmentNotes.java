package com.vados.notebook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.vados.notebook.placeholder.PlaceholderContent;

public class ItemFragmentNotes extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragmentNotes() {
    }

    // TODO: Customize parameter initialization
    /*@SuppressWarnings("unused")
    public static ItemFragmentNotes newInstance(int columnCount) {
        ItemFragmentNotes fragment = new ItemFragmentNotes();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_notes_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            PlaceholderContent placeholderContent = new PlaceholderContent();
            //ковыряем адаптер
            try{
                for(int i=1;i<=MainActivity.notes.getNotesSize();i++){
                    placeholderContent.addItem(i,
                            MainActivity.notes.getNameForId(i-1),
                            MainActivity.notes.getNoteForId(i-1));
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }


            placeholderContent.setCount(MainActivity.notes.getNotesSize()); // передаём количество элементов
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(placeholderContent.getItems()));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}