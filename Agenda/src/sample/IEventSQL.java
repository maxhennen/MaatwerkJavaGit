package sample;

import javax.print.DocFlavor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maxhe on 2-5-2017.
 */
public interface IEventSQL  {
    public void addEvent(Event newEvent);
    public ArrayList<Event> getEventsByMonth(String startMonth, String endMonth);
    public void updateEvent(Event event);
    public void deleteEvent(int id);
}
