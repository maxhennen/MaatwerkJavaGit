package sample.ViewModel;

import sample.DomainClasses.Ingredienten;
import sample.Interfaces.IIngredientenUI;

import java.util.ArrayList;

/**
 * Created by maxhe on 17-5-2017.
 */
public class IngredientenUIRepo {
    private IIngredientenUI Context;
    public IngredientenUIRepo(IIngredientenUI context){
        Context = context;
    }

    public ArrayList<Ingredienten> AlleIngredienten(){
        return Context.AlleIngredienten();
    }

    public void updateIngredient(int id,String naam, float inkoop,float verkoop,boolean halal, boolean vega){
        Context.updateIngredient(id,naam,inkoop,verkoop,halal,vega);
    }

    public void opslaanIngredient(String naam, float inkoop,float verkoop,boolean halal, boolean vega){
        Context.opslaanIngredient(naam,inkoop,verkoop,halal,vega);
    }
}
