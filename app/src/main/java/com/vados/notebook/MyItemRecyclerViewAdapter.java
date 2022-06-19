package com.vados.notebook;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.vados.notebook.main.ItemFragmentNotes;
import com.vados.notebook.main.MainFragment;
import com.vados.notebook.placeholder.PlaceholderContent.PlaceholderItem;
import com.vados.notebook.databinding.FragmentItemNotesBinding;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    FormatDate formatDate = new FormatDate();
    FragmentManager fragmentManager;
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
        holder.mDateView.setText(mValues.get(position).noteDate);
        fragmentManager = MainActivity.fragmentManager;

        holder.itemView.setOnClickListener(v -> {
            NoteFragment noteFragment = new NoteFragment(mValues.get(position).intDI);
            if (!MainActivity.isLandscape){
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,noteFragment)
                        .addToBackStack("NoteFragment")
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_note,noteFragment)
                        .addToBackStack("NoteFragment")
                        .commit();
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            Activity activity = MainActivity.mainFragment.getActivity();
            PopupMenu popupMenu = new PopupMenu(activity,v);
            activity.getMenuInflater().inflate(R.menu.menu_popup_note,popupMenu.getMenu());
            Resources resources = activity.getBaseContext().getResources();
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.popup_delete:
                        int size = MainActivity.notes.getNotesSize();
                        MainActivity.notes.deleteNoteForId(holder.mItem.intDI);
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, new MainFragment())
                                    .commit();
                        //ItemFragmentNotes.myItemRecyclerViewAdapter.notifyItemRemoved(holder.mItem.intDI+1);
                        //пытаюсь получить данные по размеру списка до удаления и если не выходит, то значит точно удалилось
                        try{
                            MainActivity.notes.getNameForId(size-1);
                            Toast.makeText(activity.getBaseContext(),
                                    resources.getString(R.string.Error), Toast.LENGTH_SHORT).show();
                        }catch (IndexOutOfBoundsException e){
                            Toast.makeText(activity.getBaseContext(),
                                    resources.getString(R.string.Deleted), Toast.LENGTH_SHORT).show();
                        }
                    case R.id.popup_change:
                        EnterNoteFragment enterNoteFragment = new EnterNoteFragment(mValues.get(position).intDI);
                        if (!MainActivity.isLandscape){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container,enterNoteFragment)
                                    .commit();
                        }else{
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container_note,enterNoteFragment)
                                    .commit();
                        }
                        return true;
                }
                return false;
            });
            popupMenu.show();
            return true;
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