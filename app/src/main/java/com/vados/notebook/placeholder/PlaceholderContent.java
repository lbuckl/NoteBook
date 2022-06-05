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

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<>();

    private static int COUNT = 1;

    public void setCount(int count){
        COUNT = count;
    }

    /*{
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }*/

    public void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public List<PlaceholderItem> getItems() {
        return ITEMS;
    }

    public void addItem(int position, String noteName, String note) {
        ITEMS.add(new PlaceholderItem(String.valueOf(position), noteName, note));
        ITEM_MAP.put(new PlaceholderItem(String.valueOf(position), noteName, note).id, new PlaceholderItem(String.valueOf(position), noteName, note));
    }

    public PlaceholderItem createPlaceholderItem(int position, String noteName, String note) {
        return new PlaceholderItem(String.valueOf(position), noteName, note);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String details;

        public PlaceholderItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}