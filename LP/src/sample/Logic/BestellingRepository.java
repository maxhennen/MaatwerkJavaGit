package sample.Logic;

import sample.DomainClasses.Klant;
import sample.DomainClasses.Products;
import sample.Interfaces.IBestellingSQL;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-5-2017.
 */
public class BestellingRepository {
    private IBestellingSQL Context;

    public BestellingRepository(IBestellingSQL context){
        Context = context;
    }

    public void nieuweBestelling(ArrayList<Products> producten, Klant klant, float TotaalPrijs){
        Context.nieuweBestelling(producten, klant,TotaalPrijs);
    }

    public ArrayList<Products> OmzetWinst(String datum){
        return Context.OmzetWinst(datum);
    }
}
