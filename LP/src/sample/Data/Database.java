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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OBIGL5V;databaseName=JavaLP;integratedSecurity=true");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
