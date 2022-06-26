package com.vados.notebook;

import java.util.Date;

public class Note{
    public String name;
    public String description;
    public Date date;

    public Note(String name, String description,Date date){
        this.name = name;
        this.description = description;
        this.date = date;
    }
}