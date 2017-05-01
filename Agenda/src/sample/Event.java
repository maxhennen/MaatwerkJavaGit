package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by maxhe on 1-5-2017.
 */
public class Event {
    private String Name;
    private Date Date;

    public Event(String name, Date date){
        setName(name);
        setDate(date);
    }

    public void setName(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setDate(Date date){
        Date = date;
    }

    public Date getDate(){
        return Date;
    }
}
