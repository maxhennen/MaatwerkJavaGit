package sample.Data;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import sample.DomainClasses.Klant;
import sample.DomainClasses.OverigeProducten;
import sample.DomainClasses.Pizza;
import sample.DomainClasses.Products;
import sample.Interfaces.IBestellingSQL;
import sun.util.resources.LocaleData;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by maxhe on 22-5-2017.
 */
public class BestellingSQLContext extends Database implements IBestellingSQL  {

    public void nieuweBestelling(ArrayList<Products> producten, Klant klant,float TotaalPrijs){
        try {
                addKlant(klant,TotaalPrijs);
                addBestelling(producten);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addBestelling(ArrayList<Products> producten){
        try {
            getConnection();

            for (Products p:producten) {
                if(p instanceof Pizza){

                    if(((Pizza) p).getSoort().equals("Custom")){
                        String queryCustom = "Insert into Pizza(Naam,Formaat,Vorm,Soort,Gluten)" +
                                "Values(?,?,?,?,?);";
                        Prep = Conn.prepareStatement(queryCustom);
                        Prep.setString(1,p.getNaam());
                        Prep.setFloat(2,((Pizza) p).getFormaat());
                        Prep.setString(3,((Pizza) p).getVorm().toString());
                        Prep.setString(4,((Pizza) p).getSoort());
                        Prep.setBoolean(5,((Pizza) p).getGluten());
                        Prep.executeUpdate();

                        String queryPizza = "Insert into Bestelregel(PizzaID,BestellingID,OverigeProductenID)" +
                                "Values((SELECT MAX (PizzaID) FROM Pizza),(Select Max(BestellingID) From Bestelling),null);";
                        Prep = Conn.prepareStatement(queryPizza);
                        Prep.executeUpdate();
                    }
                    else {
                        String queryPizza = "Insert into Bestelregel(PizzaID,BestellingID,OverigeProductenID)" +
                                "Values(?,(Select Max(BestellingID) From Bestelling),null);";
                        Prep = Conn.prepareStatement(queryPizza);
                        Prep.setInt(1, p.getID());
                        Prep.executeUpdate();
                    }

                }

                else if(p instanceof OverigeProducten){
                    String queryOverige = "Insert into Bestelregel(PizzaID,BestellingID,OverigeProductenID)" +
                            "Values(null,(Select Max(BestellingID) From Bestelling),?);";
                    Prep = Conn.prepareStatement(queryOverige);
                    Prep.setInt(1,p.getID());
                    Prep.executeUpdate();
                }
            }
            Conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addKlant(Klant klant,float TotaalPrijs){
        try {
            getConnection();

            if(klant.getKlantnummer() == 0 && klant.getNaam() != "" && klant.getAdres() != "") {
                String queryAddKlant = "Insert into Klant(Naam,Adres)Values(?,?);";
                Prep = Conn.prepareStatement(queryAddKlant);
                Prep.setString(1, klant.getNaam());
                Prep.setString(2, klant.getAdres());
                Prep.executeUpdate();

                String queryAddBestelling = "Insert into Bestelling(Klantnummer,DatumTijd)" +
                        "Values((Select MAX (KlantNummer)FROM Klant),?);";
                Prep = Conn.prepareStatement(queryAddBestelling);
                Prep.setString(1,DateTimeNow());
                Prep.executeUpdate();
            }

            else if(klant.getKlantnummer() > 0){
                String queryAddBestelling = "Insert into Bestelling(Klantnummer,DatumTijd)Values(?,?);";
                Prep = Conn.prepareStatement(queryAddBestelling);
                Prep.setInt(1,klant.getKlantnummer());
                Prep.setString(2,DateTimeNow());
                Prep.executeUpdate();
            }
            Conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String DateTimeNow(){
        java.util.Date date = new java.util.Date();

        return date.toString();
    }
}
