package sample;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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

    @Override
    public ArrayList<Event> getEvents() {

        try {
            ArrayList<Event> events = new ArrayList<>();
            getConnection();
            String query = "Select * from Events";
            prepStat = Conn.prepareStatement(query);
            results = prepStat.executeQuery();

            if(results.next()){

            }
            return events;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Er is iets misgegaan");
        }
        return null;
    }

    public void addEvent(Event event){
        try {
            getConnection();
            String query = "Insert Into Events(EventName,EventDescription,EventDateStart,EventDateEnd)Values?,?,?,?";
            prepStat = Conn.prepareStatement(query);
            prepStat.setString(1,event.getName());
            prepStat.setString(2,event.getDescription());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            prepStat.setDate(3, java.sql.Date.valueOf(sdf.format(event.getDateStart())));
            prepStat.setDate(4,java.sql.Date.valueOf(sdf.format(event.getDateEnd())));
            prepStat.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
