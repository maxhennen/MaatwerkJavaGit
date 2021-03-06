package sample.Data;

import com.sun.org.apache.regexp.internal.RE;
import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;
import sample.Enums.Vorm;
import sample.Interfaces.IPizzaSQL;
import sun.dc.pr.PRError;

import javax.print.DocFlavor;
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
                pizza.setNaam(VerwijderSpaties(Results.getString("Naam")));
                pizza.setFormaat(Results.getInt("Formaat"));
                pizza.setVorm(Vorm.valueOf(VerwijderSpaties(Results.getString("Vorm"))));
                pizza.setSoort(VerwijderSpaties(Results.getString("Soort")));
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

    public ArrayList<Ingredienten> IngredientenBijPizza(int id){
        try {
            ArrayList<Ingredienten> Ingredienten = new ArrayList<>();
            getConnection();
            String query = "SELECT i.* FROM Ingredienten i  JOIN PizzaIngredienten [pi] ON [pi].IngredientenID = i.IngredientenID \n" +
                    "JOIN Pizza p ON p.PizzaID = [pi].PizzaID where p.PizzaID = ?";
            Prep = Conn.prepareStatement(query);
            Prep.setInt(1,id);
            Results = Prep.executeQuery();

            while (Results.next()){
                Ingredienten ingredient = new Ingredienten();

                ingredient.setID(Results.getInt("IngredientenID"));
                ingredient.setNaam(Results.getString("Naam"));
                ingredient.setInkoop(Results.getFloat("Inkoop"));
                ingredient.setVerkoopPrijs(Results.getFloat("Verkoop"));
                ingredient.setVega(Results.getBoolean("Veganistisch"));
                ingredient.setHalal(Results.getBoolean("Halal"));

                Ingredienten.add(ingredient);
            }


            Conn.close();
            return Ingredienten;
        }
        catch (SQLException e){
            System.out.print(e);
            return null;
        }
    }


    public String VerwijderSpaties(String string){
        String[] verwijder = string.split(" ");
        return verwijder[0];
    }
}
