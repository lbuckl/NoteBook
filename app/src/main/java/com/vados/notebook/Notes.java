package com.vados.notebook;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Notes {

    private final ArrayList<String> noteNames = new ArrayList<>();
    private final ArrayList<String> createDate = new ArrayList<>();
    private final ArrayList<String> notes = new ArrayList<>();
    Calendar gcalendar = new GregorianCalendar();
    FormatDate formatDate = new FormatDate();

    //Создать запись по имени и значению
    public void addNewNote(String name, String note){
        this.noteNames.add(name);
        this.notes.add(note);
        this.createDate.add(formatDate.getCustomStringDate(gcalendar.getTime()));
    }

    //Создать запись по имени, значению и времени
    public void addNewNote(String name, String note, Date date){
        this.noteNames.add(name);
        this.notes.add(note);
        this.createDate.add(formatDate.getCustomStringDate(date));
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

    //Получим массив имён
    public ArrayList<String> getArrayNames(){
        return noteNames;
    }

    //получить Имя по id
    public String getNameForId(int id){
        return noteNames.get(id);
    }

    //получить Дату и время по id
    public String getDateForId(int id){
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

    //записать имя, значение и время по id
    public void setNote(int id,String name, String note, Date date){
        try{
            this.notes.set(id-1,note);
            this.noteNames.set(id-1,name);
            this.createDate.set(id-1,formatDate.getCustomStringDate(date));
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    //записать Дату по id
    public void setNoteDate(Date date){
        this.createDate.add(formatDate.getCustomStringDate(date));
    }

}
