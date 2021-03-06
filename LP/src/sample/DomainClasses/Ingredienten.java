package sample.DomainClasses;

import sample.Data.IngredientenSQLContext;
import sample.Interfaces.IIngredientenUI;
import sample.Logic.IngredientenRepository;
import sun.dc.pr.PRError;

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

    public void updateIngredient(int id, String naam, float inkoop,float verkoop,boolean halal, boolean vega ){
        Ingredienten ingredient = new Ingredienten();
        ingredient.setID(id);
        ingredient.setNaam(naam);
        ingredient.setInkoop(inkoop);
        ingredient.setVerkoopPrijs(verkoop);
        ingredient.setHalal(halal);
        ingredient.setVega(vega);

        update(ingredient);
    }

    public void opslaanIngredient(String naam, float inkoop,float verkoop,boolean halal, boolean vega){
        Ingredienten ingredient = new Ingredienten();
        ingredient.setNaam(naam);
        ingredient.setInkoop(inkoop);
        ingredient.setVerkoopPrijs(verkoop);
        ingredient.setHalal(halal);
        ingredient.setVega(vega);

        opslaan(ingredient);
    }

    public void verwijderIngredient(int id, String naam, float inkoop,float verkoop,boolean halal, boolean vega){
        Ingredienten ingredient = new Ingredienten();
        ingredient.setID(id);
        ingredient.setNaam(naam);
        ingredient.setInkoop(inkoop);
        ingredient.setVerkoopPrijs(verkoop);
        ingredient.setHalal(halal);
        ingredient.setVega(vega);

        verwijder(ingredient);
    }

    public void opslaan(Products product){
        setRepo().opslaan(product);
    }

    public void update(Products product){
        setRepo().update(product);
    }

    public void verwijder(Products product){setRepo().verwijder(product);}

    public String ToString(){
        return getID() + " : " + getNaam() + " - €" + afrondenVerkoopprijs(getVerkoopPrijs());
    }
}
