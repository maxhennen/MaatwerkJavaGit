package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maxhe on 2-5-2017.
 */
public class EventRepository {
    private IEventSQL Context;

    public EventRepository(IEventSQL context){
        Context = context;
    }

    public void addEvent(Event event){
        Context.addEvent(event);
    }

    public ArrayList<Event> getEventsByMonth(String startMonth, String endMonth){
        return Context.getEventsByMonth(startMonth,endMonth);
    }

    public void updateEvent(Event event){
        Context.updateEvent(event);
    }

    public void deleteEvent(int id){
        Context.deleteEvent(id);
    }
}
