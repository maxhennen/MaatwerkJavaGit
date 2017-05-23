package sample.DomainClasses;

import sample.Data.IngredientenSQLContext;
import sample.Interfaces.IIngredientenUI;
import sample.Logic.IngredientenRepository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by maxhe on 16-5-2017.
 */
public class Ingredienten extends Products implements IIngredientenUI {
    private IngredientenRepository IngredientenRepo;
    private float Inkoop;
    private boolean Vega;
    private boolean Halal;

    public void setInkoop(float inkoop){Inkoop = inkoop;}
    public void setVega(boolean vega){Vega = vega;}
    public void setHalal(boolean halal){Halal = halal;}

    public float getInkoop(){return Inkoop;}
    public boolean getVega(){return Vega;}
    public boolean getHalal(){return Halal;}

    public ArrayList<Ingredienten> AlleIngredienten(){
        IngredientenRepo = new IngredientenRepository(new IngredientenSQLContext());
        return IngredientenRepo.AlleIngredienten();
    }


    public String ToString(){
        return getID() + " : " + getNaam() + " - " + afrondenVerkoopprijs(getVerkoopPrijs()*100);
    }
}
