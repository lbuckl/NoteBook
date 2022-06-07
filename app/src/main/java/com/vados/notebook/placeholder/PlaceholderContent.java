package com.vados.notebook.placeholder;

import java.util.ArrayList;
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

    public void addItem(int position, String noteName, String note) {
        ITEMS.add(new PlaceholderItem(position, noteName, note));

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

        public PlaceholderItem(int id, String content, String details) {
            intDI = id;
            this.id = String.valueOf(id);
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}