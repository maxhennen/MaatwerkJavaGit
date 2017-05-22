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

            getConnection();
            String[] queries = new String[3];
            int i = 0;
            if (klant.getKlantnummer() == 0) {
                queries[i] = "Insert into Klant(Naam,Adres)Values(?,?);";
                Prep.setString(1, klant.getNaam());
                Prep.setString(2,klant.getAdres());
                Prep.addBatch();

                i++;

                queries[i] = "Insert into Bestelling(DatumTijd,TotaalPrijs,Klantnummer)Values(?,?,(Select MAX (Klantnummer) From Klant));";
                Prep.setDate(1, DateTimeNow());
                Prep.setFloat(2,TotaalPrijs);
                i++;
                Prep.addBatch();
            }

            else if(klant.getAdres() != null && klant.getNaam() != null){
                queries[i] = "Insert into Bestelling(DatumTijd,TotaalPrijs,Klantnummer)Values(?,?,?);";
                Prep.setDate(1,DateTimeNow());
                Prep.setFloat(2,TotaalPrijs);
                Prep.setInt(3,klant.getKlantnummer());
                i++;
                Prep.addBatch();
            }

            for (Products P:producten) {
                queries[i] = "Insert into Bestelregel(PizzaID,BestellingID,OverigeProductenID)Values(?,(Select Max (BestellingID) From Bestelling),?);";
                if(P instanceof Pizza){
                    Prep.setInt(1,P.getID());
                    Prep.setNull(2,0);

                    Prep.addBatch();
                }
                else if(P instanceof OverigeProducten){
                    Prep.setNull(1,0);
                    Prep.setInt(2,P.getID());
                }
            }

            Prep.executeBatch();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Date DateTimeNow(){
        java.util.Date date = new java.util.Date();

        return (Date) date;
    }
}
