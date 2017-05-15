package sample;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maxhe on 1-5-2017.
 */

public class Event implements java.io.Serializable{
    private int ID;
    private String Name;
    private String DateEnd;
    private String DateStart;
    private String Description;
    private EventRepository eventRepo;
    public Event(){

    }

    public void setID(int id){ID = id;}
    public int getID(){return ID;}
    public void setName(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setDateEnd(String date){
        DateEnd = date;
    }

    public String getDateEnd(){return DateEnd;}

    public String getDateStart(){return DateStart;}

    public void setDateStart(String date){DateStart = date;}

    public void setDescription(String description){Description = description;}

    public String getDescription(){return Description;}


    public void addEvent (Event event){
        eventRepo = new EventRepository(new EventSQLContext());
        eventRepo.addEvent(event);
    }

    public ArrayList<Event> getEventsByMonth(String startMonth, String endMonth){
        eventRepo = new EventRepository(new EventSQLContext());
        return eventRepo.getEventsByMonth(startMonth,endMonth);
    }

    public String ToString(int id, String name, String dateStart, String dateEnd){
        try {
            return id + ":" + name + " " + dateStart + " t/m " + dateEnd;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void updateEvent(Event event){
        eventRepo = new EventRepository(new EventSQLContext());
        eventRepo.updateEvent(event);
    }

    public void deleteEvent(int id){
        eventRepo = new EventRepository(new EventSQLContext());
        eventRepo.deleteEvent(id);
    }
}
