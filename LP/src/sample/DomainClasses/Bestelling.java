package sample.DomainClasses;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sample.Data.BestellingSQLContext;
import sample.Interfaces.IBestellingSQL;
import sample.Interfaces.IBestellingUI;
import sample.Logic.BestellingRepository;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Bestelling implements IBestellingUI {
    private ArrayList<Products> Producten = new ArrayList<>();
    private BestellingRepository BestellingRepo;
    private float TotaalPrijs;

    public void setProducten(ArrayList<Products> producten){Producten = producten;}
    public ArrayList<Products> getProducten(){return Producten;}

    public Pizza nieuwePizza(String naam, String vorm, float x,float y, float z, String soort, ArrayList<Ingredienten> ingredienten, boolean gluten){
        Pizza pizza = new Pizza();
        pizza.setNaam(naam);
        pizza.setVorm(sample.Enums.Vorm.valueOf(vorm));
        pizza.setFormaat(pizza.formaatBerekenen(vorm,x,y,z));
        pizza.setSoort(soort);
        pizza.setIngredienten(ingredienten);
        pizza.setGluten(gluten);
        pizza.setVerkoopPrijs(pizza.prijsBepalen(ingredienten,pizza.formaatBerekenen(vorm,x,y,z)));
        Producten.add(pizza);
        return pizza;
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
        BerekenTotaalPrijs();
        BestellingRepo.nieuweBestelling(getProducten(), klant,TotaalPrijs);
        KlantbonOpslaan(naam);
        JOptionPane.showMessageDialog(null,"Bestelling is gelukt. Bon is opgeslagen.");
    }

    public void BerekenTotaalPrijs(){
        for (Products P:Producten) {
            TotaalPrijs = TotaalPrijs + P.getVerkoopPrijs();
        }
    }

    public void KlantbonOpslaan(String klant){
        try {
            Date date = new Date();
            float excBTW = 0;
            float totaalprijs = 0;
            ArrayList<String> lines = new ArrayList<>();
            lines.add("Klantbon voor: " + klant);

            for (Products p : Producten) {
                lines.add(p.getNaam()+ " : " + String.valueOf(p.getVerkoopPrijs()));
                excBTW = excBTW + PrijsExclBTW(p);
                totaalprijs = totaalprijs + p.getVerkoopPrijs();
            }
            lines.add("Prijs excl BTW: " + String.valueOf(excBTW));
            lines.add("BTW: " +  String.valueOf(totaalprijs - excBTW));
            lines.add("Totaalprijs: " + String.valueOf(totaalprijs));

            FileWriter fileWriter = new FileWriter(date.toString());
            for(String s:lines){
                fileWriter.write(s);
            }
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public float PrijsExclBTW(Products product){
        float excBTW = 0;
        if(product instanceof OverigeProducten){
            OverigeProducten overigeProducten = (OverigeProducten) product;
            if(overigeProducten.getAlcohol()){
                excBTW = overigeProducten.getVerkoopPrijs() *(float) 0.21;
            }
            else {
                excBTW = overigeProducten.getVerkoopPrijs() * (float) 0.06;
            }
        }
        else {
            excBTW = product.getVerkoopPrijs() * (float) 0.06;
        }
        return excBTW;
    }
}
