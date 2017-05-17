package sample.Data;

import java.sql.*;

/**
 * Created by maxhe on 17-5-2017.
 */
public class Database {
    public Connection Conn;
    public PreparedStatement Prep;
    public ResultSet Results;

    public void getConnection(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Conn = DriverManager.getConnection("jdbc:odbc:JavaLP","DESKTOP-VR2JK4O",null);
        }
        catch (ClassNotFoundException e){
            System.out.print(e);
        }
        catch (SQLException e){
            System.out.print(e);
        }
    }
}
