package sample.DomainClasses;

import sample.Data.BestellingSQLContext;
import sample.Interfaces.IBestellingSQL;
import sample.Interfaces.IBestellingUI;
import sample.Logic.BestellingRepository;

import java.util.ArrayList;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Bestelling implements IBestellingUI {
    private ArrayList<Products> Producten;
    private BestellingRepository BestellingRepo;
    private float TotaalPrijs;

    public void setProducten(ArrayList<Products> producten){Producten = producten;}
    public ArrayList<Products> getProducten(){return Producten;}

    public void nieuwePizza(String naam, String vorm, float x,float y, float z, String soort, ArrayList<Ingredienten> ingredienten, boolean gluten){
        Pizza pizza = new Pizza();
        pizza.setNaam(naam);
        pizza.setVorm(sample.Enums.Vorm.valueOf(vorm));
        pizza.setFormaat(pizza.formaatBerekenen(vorm,x,y,z));
        pizza.setSoort(soort);
        pizza.setIngredienten(ingredienten);
        pizza.setGluten(gluten);
        pizza.setVerkoopPrijs(pizza.prijsBepalen(ingredienten,pizza.formaatBerekenen(vorm,x,y,z)));
        Producten.add(pizza);
    }

    public void productToevoegen(Products product){
        Producten.add(product);
    }

    public void nieuweBestelling(int klantnummer, String naam, String adres){
        BestellingRepo = new BestellingRepository(new BestellingSQLContext());

        Klant klant = new Klant();
        klant.setKlantnummer(klantnummer);
        klant.setNaam(naam);
        klant.setAdres(adres);

        BestellingRepo.nieuweBestelling(Producten, klant,TotaalPrijs);
    }

    public void BerekenTotaalPrijs(){
        for (Products P:Producten) {
            TotaalPrijs = TotaalPrijs + P.getVerkoopPrijs();
        }
    }
}
