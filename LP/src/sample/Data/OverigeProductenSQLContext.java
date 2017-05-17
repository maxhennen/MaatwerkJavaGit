package sample.Data;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.OverigeProducten;
import sample.Interfaces.IOverigeProductenSQL;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class OverigeProductenSQLContext extends Database implements IOverigeProductenSQL {

    public ArrayList<OverigeProducten> AlleOverige(){
        try {
            ArrayList<OverigeProducten> producten = new ArrayList<>();
            getConnection();

            String query = "SELECT * FROM OverigeProducten";
            Prep = Conn.prepareStatement(query);
            Results = Prep.executeQuery();

            while (Results.next()){

                OverigeProducten product = new OverigeProducten();
                product.setID(Results.getInt("ProductID"));
                product.setNaam(Results.getString("Naam"));
                product.setAlcohol(Results.getBoolean("Alcoholisch"));
                product.setInkoop(Results.getFloat("Inkoop"));
                product.setVerkoopPrijs(Results.getFloat("Verkoop"));

                producten.add(product);
            }
            Conn.close();
            return producten;
        }
        catch (SQLException e){
            System.out.print(e);
            return null;
        }
    }
}
