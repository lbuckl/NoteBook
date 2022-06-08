package com.vados.notebook.placeholder;

import androidx.annotation.NonNull;

import com.vados.notebook.FormatDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    //Список элементов
    public static final List<PlaceholderItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<>();

    public List<PlaceholderItem> getItems() {
        return ITEMS;
    }

    public void addItem(int position, String noteName, String note, Date noteDate) {
        ITEMS.add(new PlaceholderItem(position, noteName, note, noteDate));

        //ITEM_MAP.put(new PlaceholderItem(String.valueOf(position), noteName, note)
        // .id, new PlaceholderItem(String.valueOf(position), noteName, note));
    }

    public void clearItems(){
        ITEMS.clear();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public int intDI;
        public final String content;
        public final String details;
        public final Date noteDate;


        public PlaceholderItem(int id, String content, String details, Date date) {
            this.noteDate = date;
            this.intDI = id;
            this.id = String.valueOf(id);
            this.content = content;
            this.details = details;
        }

        @NonNull
        @Override
        public String toString() {
            return content;
        }
    }
}