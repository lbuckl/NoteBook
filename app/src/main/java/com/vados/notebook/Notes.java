package com.vados.notebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class Notes {

    private ArrayList<String> noteNames = new ArrayList<>();
    private ArrayList<Date> createDate = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();
    GregorianCalendar gcalendar = new GregorianCalendar();

    //Создать запись только по имени
    public void addNewNote(String name){
        this.noteNames.add(name);
        this.notes.add("");
        this.createDate.add(gcalendar.getTime());
    }

    //Создать запись только по имени и значению
    public void addNewNote(String name, String note){
        this.noteNames.add(name);
        this.notes.add(note);
        this.createDate.add(gcalendar.getTime());
    }

    public void deleteNoteForId(int id){
        noteNames.remove(id-1);
        notes.remove(id-1);
    }

    //Получаем число заметок
    public int getNotesSize(){
        return noteNames.size();
    }

    //Получим массив имён
    public ArrayList<String> getArrayNames(){
        return noteNames;
    }

    //получить Имя по id
    public String getNameForId(int id){
        return noteNames.get(id);
    }

    //получить Дату и время по id
    public Date getTimeForId(int id){
        return createDate.get(id);
    }

    //получить Значение по id
    public String getNoteForId(int id){
        return notes.get(id);
    }

    //записать имя и значение по id
    public void setNote(int id,String name, String note){
        try{
            this.notes.set(id-1,note);
            this.noteNames.set(id-1,name);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }
    //записать Дату по id
    public void setNoteDate(Date date){
        this.createDate.add(date);
    }

}
