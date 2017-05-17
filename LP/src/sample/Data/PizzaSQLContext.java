package sample.Data;

import com.sun.org.apache.regexp.internal.RE;
import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;
import sample.Enums.Vorm;
import sample.Interfaces.IPizzaSQL;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class PizzaSQLContext extends Database implements IPizzaSQL{

    public ArrayList<Pizza> AlleStandaardPizzas(){
        try {
            ArrayList<Pizza> pizzas = new ArrayList<>();
            getConnection();

            String query = "SELECT * FROM Pizza WHERE Soort like 'Standaard'";
            Prep = Conn.prepareStatement(query);
            Results = Prep.executeQuery();

            while (Results.next()){

                Pizza pizza = new Pizza();
                pizza.setID(Results.getInt("PizzaID"));
                pizza.setNaam(Results.getString("Naam"));
                pizza.setFormaat(Results.getInt("Formaat"));
                pizza.setVorm(Vorm.valueOf(Results.getString("Vorm")));
                pizza.setSoort(Results.getString("Soort"));
                pizza.setGluten(Results.getBoolean("Gluten"));

                pizzas.add(pizza);
            }
            Conn.close();
            return pizzas;
        }
        catch (SQLException e){
            System.out.print(e);
            return null;
        }
    }
}
