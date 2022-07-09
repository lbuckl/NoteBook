package com.vados.notebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Notes {
    private ArrayList<String> noteNames = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();
    private ArrayList<Date> createDate = new ArrayList<>();

    Calendar gcalendar = new GregorianCalendar();

    protected Notes() {

    }

    //Создать запись по имени и значению
    public void addNewNote(String name, String note){
        this.noteNames.add(name);
        this.notes.add(note);
        this.createDate.add(gcalendar.getTime());
        //addObjToFireStore(noteNames.size()-1);
    }

    //Создать запись по имени, значению и времени
    public void addNewNote(String name, String note, Date date){
        this.noteNames.add(name);
        this.notes.add(note);
        this.createDate.add(date);
        //addObjToFireStore(noteNames.size()-1);
    }

    public void deleteNoteForId(int id){
        noteNames.remove(id-1);
        notes.remove(id-1);
        createDate.remove(id-1);
    }

    //Получаем число заметок
    public int getNotesSize(){
        return noteNames.size();
    }

    //получить Имя по id
    public String getNameForId(int id){
        return noteNames.get(id);
    }

    //получить Дату и время по id
    public String getDateForId(int id){
        FormatDate formatDate = new FormatDate(); // находится именно тут, чтобы менялся язык вывода
        return formatDate.getCustomStringDate(createDate.get(id));
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

    //записать имя, значение и время по id
    public void setNote(int id,String name, String note, Date date){
        try{
            this.notes.set(id-1,note);
            this.noteNames.set(id-1,name);
            this.createDate.set(id-1,date);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public void addObjToFireStore(int id){
        Note note = new Note(noteNames.get(id),notes.get(id));
        //MainActivity.collection.document("note").set(note);
    }
}

