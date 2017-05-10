package sample;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by maxhe on 2-5-2017.
 */
public class EventSQLContext implements IEventSQL {

    private Connection Conn;
    private PreparedStatement prepStat;
    private ResultSet results;

    public void getConnection(){

            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Conn = DriverManager.getConnection("jdbc:odbc:JavaAgendaDB","DESKTOP-VR2JK4O",null);
            }

             catch (Exception e) {
                System.out.println(e);
            }
    }

    public void addEvent(Event event){
        try {
            getConnection();
            String query = "Insert Into Events(EventName,EventDescription,EventDateStart,EventDateEnd)Values(?,?,?,?)";
            prepStat = Conn.prepareStatement(query);
            prepStat.setString(1,event.getName());
            prepStat.setString(2,event.getDescription());

            prepStat.setString(3,event.getDateStart().toString());
            prepStat.setString(4, event.getDateEnd().toString());
            prepStat.executeUpdate();
            Conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Event> getEventsByMonth(Calendar startMonth, Calendar endMonth){
        try{
            ArrayList<Event> events = new ArrayList<Event>();
            getConnection();
            String query = "SELECT * FROM Events WHERE EventDateStart >= ? and EventDateStart <= ?  ";


            prepStat = Conn.prepareStatement(query);
            prepStat.setTimestamp(1,new java.sql.Timestamp(startMonth.getTimeInMillis()));
            prepStat.setTimestamp(2,new java.sql.Timestamp(endMonth.getTimeInMillis()));
            results = prepStat.executeQuery();

            while(results.next()){
                Event event = new Event();
                event.setName(results.getString("EventName"));
                event.setDescription(results.getString("EventDescription"));

                java.sql.Date dateStart = java.sql.Date.valueOf(results.getDate("EventDateStart").toString());
                Calendar calStart = new GregorianCalendar();
                calStart.setTime(dateStart);
                event.setDateStart(calStart);

                java.sql.Date dateEnd = java.sql.Date.valueOf(results.getDate("EventDateEnd").toString());
                Calendar calEnd = new GregorianCalendar();
                calEnd.setTime(dateEnd);
                event.setDateEnd(calEnd);

                events.add(event);
            }
            return events;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
