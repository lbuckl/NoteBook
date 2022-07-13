package com.vados.notebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormatDate {
    private Date date = null;
    private String sDate = "";
    private final Calendar gcalendar;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM y E HH:mm");

    {
        gcalendar = new GregorianCalendar();
        dateFormat.setCalendar(gcalendar);
        sDate = dateFormat.format(gcalendar.getTime());
    }

    public String getStringDate(){
        return sDate;
    }

    public String getCustomStringDate(Date date){
        gcalendar.setTime(date);
        return dateFormat.format(gcalendar.getTime());
    }

}
