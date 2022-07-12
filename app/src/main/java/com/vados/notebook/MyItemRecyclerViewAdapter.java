package com.vados.notebook;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vados.notebook.placeholder.PlaceholderContent.PlaceholderItem;
import com.vados.notebook.databinding.FragmentItemNotesBinding;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    FormatDate formatDate = new FormatDate();
    //Конструктор
    public MyItemRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    //метод вызывается для создания объекта ViewHolder,
    // в конструктор которого необходимо передать созданный View-компонент,
    // с которым в дальнейшем будут связываться java объекты
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemNotesBinding
                .inflate(LayoutInflater
                        .from(parent.getContext()), parent, false));
    }

    //метод отвечает за связь java объекта и View
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mDateView.setText(formatDate.getCustomStringDate(mValues.get(position).noteDate));

        holder.itemView.setOnClickListener(v -> {
            FragmentManager fragmentManager = MainActivity.fragmentManager;
            EnterNoteFragment enterNoteFragmentRep = new EnterNoteFragment(mValues.get(position).intDI);
            if (!MainActivity.isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,enterNoteFragmentRep)
                        .addToBackStack("NoteFragment")
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,enterNoteFragmentRep)
                        .addToBackStack("NoteFragment")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //предоставит нам доступ к View-компонентам
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mDateView;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentItemNotesBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mDateView = binding.date;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}