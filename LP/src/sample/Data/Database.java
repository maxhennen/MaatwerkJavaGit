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
            Class.forName("j");
            Conn = DriverManager.getConnection("jdbc:odbc:JavaLP","DESKTOP-OBIGL5V",null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
