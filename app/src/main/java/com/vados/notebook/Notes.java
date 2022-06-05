package com.vados.notebook;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Notes {

    private ArrayList<String> noteNames;
    private ArrayList<Date> createDate;
    private ArrayList<String> notes;
    GregorianCalendar gcalendar = new GregorianCalendar();

    //Создать запись только по имени
    public void addNewNote(String name){

    }

    //Создать запись только по имени и значению
    public void addNewNote(String name, String note){
        noteNames.add(name);
        notes.add(note);
        gcalendar.getTime();
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

    //записать Имя по id
    public void setNoteName(String name){
        noteNames.add(name);
    }

    //записать Значение по id
    public void setNote(String note){
        notes.add(note);
    }
    //записать Дату по id
    public void setNoteDate(Date date){
        createDate.add(date);
    }

}
