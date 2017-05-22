package sample.ViewModel;

import sample.DomainClasses.Ingredienten;
import sample.DomainClasses.Products;
import sample.Interfaces.IBestellingUI;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-5-2017.
 */
public class BestellingUIRepo {
    private IBestellingUI Context;

    public BestellingUIRepo(IBestellingUI context){
        Context = context;
    }

    public void nieuwePizza(String naam, String vorm, float x, float y, float z, String bodemSoort,
                            ArrayList<Ingredienten> ingredienten, boolean gluten){
        Context.nieuwePizza(naam,vorm, x,y,z,bodemSoort,ingredienten,gluten);
    }

    public void productToevoegen(Products product){
        Context.productToevoegen(product);
    }

    public void nieuweBestelling(int klantnummer, String naam, String adres){
        Context.nieuweBestelling(klantnummer,naam,adres);
    }
}
