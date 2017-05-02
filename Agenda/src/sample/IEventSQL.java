package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by maxhe on 2-5-2017.
 */
public interface IEventSQL  {
    public ArrayList<Event> getEvents();
    public void addEvent(Event newEvent);
}
