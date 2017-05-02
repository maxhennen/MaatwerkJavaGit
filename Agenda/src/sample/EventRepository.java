package sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by maxhe on 2-5-2017.
 */
public class EventRepository {
    private IEventSQL Context;

    public EventRepository(IEventSQL context){
        Context = context;
    }

    public ArrayList<Event> getEvents(){
        return Context.getEvents();
    }
}
