package sample.Data;

import com.sun.org.apache.regexp.internal.RE;
import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Pizza;
import sample.Enums.Vorm;
import sample.Interfaces.IIngredientenSQL;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class IngredientenSQLContext extends Database implements IIngredientenSQL {

    public ArrayList<Ingredienten> AlleIngredienten(){
        try {
            ArrayList<Ingredienten> ingredientens = new ArrayList<>();
            getConnection();

            String query = "SELECT * FROM Ingredienten";
            Prep = Conn.prepareStatement(query);
            Results = Prep.executeQuery();

            while (Results.next()){

                Ingredienten ingredient = new Ingredienten();
                ingredient.setID(Results.getInt("IngredientenID"));
                ingredient.setNaam(Results.getString("Naam"));
                ingredient.setInkoop(Results.getFloat("Inkoop"));
                ingredient.setVerkoopPrijs(Results.getFloat("Verkoop"));
                ingredient.setVega(Results.getBoolean("Veganistisch"));
                ingredient.setHalal(Results.getBoolean("Halal"));

                ingredientens.add(ingredient);
            }
            Conn.close();
            return ingredientens;
        }
        catch (SQLException e){
            System.out.print(e);
            return null;
        }
    }

    public void updateIngredient(Ingredienten ingredient){
        try {
            getConnection();
            String query = "UPDATE Ingredienten SET Naam = ?,Inkoop = ?, Verkoop = ?,Veganistisch = ?,Halal = ? WHERE IngredientenID = ? ;";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,ingredient.getNaam());
            Prep.setFloat(2,ingredient.getInkoop());
            Prep.setFloat(3,ingredient.getVerkoopPrijs());
            Prep.setBoolean(4,ingredient.getHalal());
            Prep.setBoolean(5,ingredient.getVega());
            Prep.setInt(6,ingredient.getID());
            Prep.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
