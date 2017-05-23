package sample.Interfaces;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Klant;
import sample.DomainClasses.Pizza;
import sample.DomainClasses.Products;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-5-2017.
 */
public interface IBestellingUI {

    Pizza nieuwePizza(String naam, String vorm, float x, float y, float z, String bodemSoort,
                      ArrayList<Ingredienten> ingredienten, boolean gluten);
    void productToevoegen(Products product);

    void nieuweBestelling(int klantnummer, String naam, String adres);
}
