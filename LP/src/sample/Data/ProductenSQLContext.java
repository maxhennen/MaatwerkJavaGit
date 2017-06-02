package sample.Data;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.OverigeProducten;
import sample.DomainClasses.Pizza;
import sample.DomainClasses.Products;
import sample.Interfaces.IProducten;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by maxhe on 2-6-2017.
 */
public class ProductenSQLContext extends Database implements IProducten {

    public void opslaan(Products product){
        try {
            getConnection();
            if(product instanceof Pizza){
                Pizza pizza = (Pizza) product;
                String query = "INSERT INTO Pizza(Naam,Formaat,Vorm,Soort,Gluten)Values(?,?,?,?,?);";
                Prep = Conn.prepareStatement(query);

                Prep.setString(1,pizza.getNaam());
                Prep.setFloat(2,pizza.getFormaat());
                Prep.setString(3,pizza.getVorm().toString());
                Prep.setString(4,"Standaard");
                Prep.setBoolean(5,pizza.getGluten());
                Prep.executeUpdate();
            }

            else if(product instanceof OverigeProducten){
                OverigeProducten overig = (OverigeProducten) product;
                String query = "UPDATE OverigeProducten SET Naam = ?, Alcoholisch = ?, Inkoop = ?, Verkoop = ? WHERE ProductID = ?;";
                Prep = Conn.prepareStatement(query);
                Prep.setString(1,overig.getNaam());
                Prep.setBoolean(2,overig.getAlcohol());
                Prep.setFloat(3,overig.getInkoop());
                Prep.setFloat(4,overig.getVerkoopPrijs());
                Prep.setInt(5,overig.getID());
                Prep.executeUpdate();
            }

            else if(product instanceof Ingredienten){
                Ingredienten ingredient = (Ingredienten) product;
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
            Conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Products product){

    }
}
