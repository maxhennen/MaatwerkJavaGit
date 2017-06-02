package sample.DomainClasses;

import sample.Data.OverigeProductenSQLContext;
import sample.Interfaces.IOverigeProductenUI;
import sample.Logic.OverigeProductenRepository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by maxhe on 16-5-2017.
 */
public class OverigeProducten extends Products implements IOverigeProductenUI{
    private boolean Alcohol;
    private float Inkoop;
    private OverigeProductenRepository OverigeRepo;

    public void setAlcohol(boolean alcohol){Alcohol = alcohol;}
    public void setInkoop(float inkoop){Inkoop = inkoop;}

    public boolean getAlcohol(){return Alcohol;}
    public float getInkoop(){return Inkoop;}

    public ArrayList<OverigeProducten> AlleOverige(){
        OverigeRepo = new OverigeProductenRepository(new OverigeProductenSQLContext());
        return OverigeRepo.AlleOverige();
    }

    public void updateOverig(int id, String naam, float inkoop, float verkoop, boolean alcohol){
        OverigeProducten overig = new OverigeProducten();
        overig.setID(id);
        overig.setNaam(naam);
        overig.setInkoop(inkoop);
        overig.setVerkoopPrijs(verkoop);
        overig.setAlcohol(alcohol);

        OverigeRepo = new OverigeProductenRepository(new OverigeProductenSQLContext());
    }

    public void opslaan(Products product){

    }

    public void update(Products product){

    }

    public String ToString(){
        return getID() + " : " + getNaam() + " - " + afrondenVerkoopprijs(getVerkoopPrijs()*100);
    }

}
