package sample;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
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

            prepStat.setString(3,event.getDateStart());
            prepStat.setString(4,event.getDateEnd());
            prepStat.executeUpdate();
            Conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Event> getEventsByMonth(String  startMonth, String endMonth){
        try{
            ArrayList<Event> events = new ArrayList<Event>();
            getConnection();
            String query = "SELECT * FROM Events";


            prepStat = Conn.prepareStatement(query);
            results = prepStat.executeQuery();

            while(results.next()){
                Event event = new Event();
                event.setID(results.getInt("id"));
                event.setName(results.getString("EventName"));
                event.setDescription(results.getString("EventDescription"));

                String dateStart = results.getString("EventDateStart");
                String dateEnd = results.getString("EventDateEnd");

                event.setDateStart(dateStart);
                event.setDateEnd(dateEnd);

                events.add(event);
            }
            return events;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void updateEvent(Event event){
        try{
            getConnection();
            String query = "UPDATE Events SET EventName = ?, EventDescription = ?, EventDateStart = ?, EventDateEnd = ? WHERE id = ?";
            prepStat = Conn.prepareStatement(query);
            prepStat.setString(1,event.getName());
            prepStat.setString(2,event.getDescription());

            prepStat.setString(3,event.getDateStart());
            prepStat.setString(4,event.getDateEnd());
            prepStat.setInt(5,event.getID());
            prepStat.executeUpdate();
            Conn.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }

    public void deleteEvent(int id){
        try{
            getConnection();
            String query = "DELETE  FROM Events WHERE id = ?";
            prepStat = Conn.prepareStatement(query);
            prepStat.setInt(1,id);
            prepStat.executeUpdate();
            Conn.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}
