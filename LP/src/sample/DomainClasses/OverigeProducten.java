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

    public String ToString(){
        return getID() + " : " + getNaam() + " - " + afrondenVerkoopprijs(getVerkoopPrijs()*100);
    }

}
