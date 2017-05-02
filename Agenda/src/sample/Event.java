package sample;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by maxhe on 1-5-2017.
 */

public class Event implements java.io.Serializable{
    private String Name;
    private DateTimeDV DateEnd;
    private DateTimeDV DateStart;
    private String Description;
    private EventRepository eventRepo;
    public Event(){

    }

    public void setName(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setDateEnd(DateTimeDV date){
        DateEnd = date;
    }

    public DateTimeDV getDateEnd(){return DateEnd;}

    public DateTimeDV getDateStart(){return DateStart;}

    public void setDateStart(DateTimeDV date){DateStart = date;}

    public void setDescription(String description){Description = description;}

    public String getDescription(){return Description;}


    public ArrayList<Event> getEvents() {
        eventRepo = new EventRepository(new EventSQLContext());
        return eventRepo.getEvents();
    }
}
