package sample.Interfaces;

import sample.DomainClasses.Klant;
import sample.DomainClasses.Products;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-5-2017.
 */
public interface IBestellingSQL {
    void nieuweBestelling(ArrayList<Products> producten, Klant klant,float TotaalPrijs);
}
