package com.vados.notebook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.vados.notebook.MainActivity;
import com.vados.notebook.MyItemRecyclerViewAdapter;
import com.vados.notebook.R;
import com.vados.notebook.placeholder.PlaceholderContent;

public class ItemFragmentNotes extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameters
    private int mColumnCount = 1;
    public static MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;

    public ItemFragmentNotes() {
    }

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
            placeholderContent.clearItems();

            try{
                int noteSize = MainActivity.notes.getNotesSize();
                if (noteSize >=1) {
                    for (int i = 1; i <= noteSize; i++) {
                        placeholderContent.addItem(i,
                                MainActivity.notes.getNameForId(i - 1),
                                MainActivity.notes.getNoteForId(i - 1),
                                MainActivity.notes.getDateForId(i - 1));
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(placeholderContent.getItems());
            recyclerView.setAdapter(myItemRecyclerViewAdapter); //передаём
            // Добавим разделитель карточек
            DividerItemDecoration itemDecoration = new
                    DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            //Включаем анимацию
            DefaultItemAnimator animator = new DefaultItemAnimator();
            animator.setAddDuration(1000);
            animator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(animator);

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}